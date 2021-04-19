package me.capthua.advancedjava.week5.homework10;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HikariDemo {

    public static void main(String[] args) {
        ApplicationContext context=new ClassPathXmlApplicationContext("dbcp.xml");
        DataSource ds= (HikariDataSource)context.getBean("dataSource");
        try {
            Connection connection=ds.getConnection();
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);

            //创建
            String createUserSql="insert into user(id,name) values(?,?)";
            PreparedStatement createUserStat=connection.prepareStatement(createUserSql);
            createUserStat.setInt(1,2);
            createUserStat.setString(2,"han");
            int createResult = createUserStat.executeUpdate();
            connection.commit();
            //其他同jdbcdemo
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("hehe");
    }

}
