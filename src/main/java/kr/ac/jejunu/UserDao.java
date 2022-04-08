package kr.ac.jejunu;

import java.sql.*;

public class UserDao {
    private final JdbcContext jdbcContext; //콩에서 받은 것

    public UserDao(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext; //생성자
    }

    public User findById(Integer id) throws SQLException {
        StatementStrategy statementStrategy = connection -> { //Template callback pattern, 람다
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from userinfo where id = ?"
            );
            preparedStatement.setInt(1, id);
            return preparedStatement;
        }; //id(dependency)를 생성자로 넘김
        return jdbcContext.jdbcContextForFind(statementStrategy);
    }

    public void insert(User user) throws SQLException {
        StatementStrategy statementStrategy = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into userinfo(name, password) values ( ?, ? )"
                    , Statement.RETURN_GENERATED_KEYS
            );
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            return preparedStatement;
        }; //변하는 것을 추출 Strategy
        jdbcContext.jdbcContextForInsert(user, statementStrategy); //변하지 않은 것을 추출 ConText
    }

    public void update(User user) throws SQLException {
        StatementStrategy statementStrategy = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "update userinfo set name = ?, password = ? where id = ?"
            );
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setInt(3, user.getId());
            return preparedStatement;
        };
        jdbcContext.jdbcContextForUpdate(statementStrategy);
    }

    public void delete(Integer id) throws SQLException {
        StatementStrategy statementStrategy = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "delete from userinfo where id = ?"
            );
            preparedStatement.setInt(1, id);
            return preparedStatement;
        };
        jdbcContext.jdbcContextForUpdate(statementStrategy);
    }

}


