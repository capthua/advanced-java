package me.capthua.advancedjava.week5.homework10;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JdbcDemo {

    public static Connection getConnection() throws SQLException, IOException {
        Properties properties = new Properties();
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("database.properties");
        properties.load(is);
        is.close();
        String url = properties.getProperty("url");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");
        return DriverManager.getConnection(url, username, password);
    }

    public static void main(String[] args) throws Exception{
        Connection conn=getConnection();
        System.out.println("hehe");

        conn.setAutoCommit(true);
        conn.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);

        //创建
        //String createUserSql="insert into user(id,name) values(?,?)";
        //PreparedStatement createUserStat=conn.prepareStatement(createUserSql);
        //createUserStat.setInt(1,2);
        //createUserStat.setString(2,"han");
        //int createResult = createUserStat.executeUpdate();

        //查询
        String selectUsers="select * from user where id=? and name=? ";
        PreparedStatement statement = conn.prepareStatement(selectUsers);
        statement.setString(1,"02baf23d3efd289d");
        statement.setString(2,"restuser043");
        ResultSet usersResultSet = statement.executeQuery(selectUsers);
        while (usersResultSet.next()){
            System.out.println(usersResultSet.getInt(1)+"--"+usersResultSet.getString(2));
        }

        //更新
        //String updateName="update user set name=? where id=?";
        //PreparedStatement stat=conn.prepareStatement(updateName);
        //
        //stat.setString(1,"sh");
        //stat.setInt(2,1);
        //stat.executeUpdate();
        //
        ////删除
        //String deleteUserSql="delete from user where id=?";
        //PreparedStatement deleteUserStat=conn.prepareStatement(deleteUserSql);
        //
        //deleteUserStat.setInt(1,2);
        //deleteUserStat.executeUpdate();


//        String selectSql="update user set username=? where id=?";
//        PreparedStatement stat=conn.prepareStatement(selectSql);
//        stat.setString(1,"han");
//        stat.setString(2,"1");


//        Blob avatar=conn.createBlob();
//        int offset=1;
//        OutputStream out = avatar.setBinaryStream(1);
//        InputStream is= Thread.currentThread().getContextClassLoader().getResourceAsStream("database.properties");
//        byte[] buffer=new byte[100];
//        int byteRead;
//        while ((byteRead=is.read(buffer))!=-1){
//            out.write(buffer,0,byteRead);
//        }
//        String updateAvatar="update user set avatar=? where id=?";
//        PreparedStatement stat=conn.prepareStatement(updateAvatar);
//
//        stat.setBlob(1,avatar);
//        stat.setString(2,"1");
//        stat.executeUpdate();


//        conn.commit();
        System.out.println("hehe");

    }

}
