package kr.ac.jejunu;

import java.sql.*;

public class UserDao {
    private final JdbcContext jdbcContext; //콩에서 받은 것

    public UserDao(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext; //생성자
    }

    public User findById(Integer id) throws SQLException {
        String sql = "select * from userinfo where id = ?";
        Object[] params = new Object[]{id};
        return jdbcContext.find(sql, params);
    }

    public void insert(User user) throws SQLException {
        String sql = "insert into userinfo(name, password) values ( ?, ? )";
        Object[] params = new Object[]{user.getName(), user.getPassword()};
        jdbcContext.insert(user, sql, params);
    }

    public void update(User user) throws SQLException {
        String sql = "UPDATE userinfo SET name = ?, password = ? WHERE id = ?";
        Object[] params = new Object[]{user.getName(), user.getPassword(), user.getId()};
        jdbcContext.update(sql, params);
    }

    public void delete(Integer id) throws SQLException {
        String sql = "DELETE FROM userinfo WHERE id = ?";
        Object[] params = new Object[]{id};
        jdbcContext.update(sql, params);
    }

}


