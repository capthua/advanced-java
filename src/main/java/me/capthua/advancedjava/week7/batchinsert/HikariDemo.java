package me.capthua.advancedjava.week7.batchinsert;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HikariDemo {

    public static void main(String[] args) {
        ApplicationContext context=new ClassPathXmlApplicationContext("dbcp.xml");
        DataSource ds= (HikariDataSource)context.getBean("dataSource");
        int count=1000000;
        int threadCount=5;
        ExecutorService service=Executors.newCachedThreadPool();
        for(int i=0;i<threadCount;i++){
            service.execute(new InsertTask(count/threadCount,ds));
        }
    }

    private static class InsertTask implements Runnable {

        private int insertCount;
        private DataSource ds;

        public InsertTask(int insertCount,DataSource ds){
            this.insertCount=insertCount;
            this.ds=ds;
        }

        @Override
        public void run() {
            try {
                String threadName=Thread.currentThread().getName();
                Connection connection=ds.getConnection();
                //connection.setAutoCommit(false);
                connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
                Date time=new Date();
                //创建订单
                System.out.println(threadName+"开始:");
                long startTime=System.currentTimeMillis();
                String createUserSql="insert into tb_order values(?,?,?,?,?,?,?,?)";
                PreparedStatement createUserStat=connection.prepareStatement(createUserSql);
                for(int i=0;i<insertCount;i++){
                    createUserStat.setString(1,i+""+Thread.currentThread().getName());
                    createUserStat.setDate(2,new java.sql.Date(time.getTime()));
                    createUserStat.setDate(3,new java.sql.Date(time.getTime()));
                    createUserStat.setFloat(4,5.5F);
                    createUserStat.setString(5,"payment_id");
                    createUserStat.setString(6,"delivery_id");
                    createUserStat.setInt(7,0);
                    createUserStat.setString(8,"userId");
                    //加入批处理
                    createUserStat.addBatch();
                }
                System.out.println(Thread.currentThread().getName()+"");
                createUserStat.executeBatch();
                //connection.commit();
                System.out.println(Thread.currentThread().getName()+"结束,耗时:"+(System.currentTimeMillis()-startTime)/1000);
                createUserStat.close();
                connection.close();
                //其他同jdbcdemo
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
