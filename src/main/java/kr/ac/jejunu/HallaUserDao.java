package kr.ac.jejunu;

import java.sql.*;

public class HallaUserDao extends UserDao {
    @Override
    public Connection getConnection() throws ClassNotFoundException, SQLException {
                //MySQL Driver 로딩
        Class.forName("com.mysql.cj.jdbc.Driver");
        //커넥션
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/jejunu", "jeju", "jeju"
        );
    }
}
