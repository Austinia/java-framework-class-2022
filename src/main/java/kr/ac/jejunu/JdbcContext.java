package kr.ac.jejunu;

import javax.sql.DataSource;
import java.sql.*;

public class JdbcContext {
    private final DataSource dataSource;//콩에서 받은 것

    public JdbcContext(DataSource dataSource) {
        this.dataSource = dataSource;
    } //생성자

    User jdbcContextForFind(StatementStrategy statementStrategy) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            connection = dataSource.getConnection();
            //sql 작성
            preparedStatement = statementStrategy.makeStatement(connection);
            //sql 실행
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                //User 에 데이터 매핑
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
            }
        } finally {
            try {
                resultSet.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                preparedStatement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    void jdbcContextForInsert(User user, StatementStrategy statementStrategy) throws SQLException {
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        try {
            //드라이버 로딩
            connection = dataSource.getConnection();
            //sql 작성
            preparedStatement = statementStrategy.makeStatement(connection);
            //sql 실행
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            //User 에 데이터 매핑
            user.setId(resultSet.getInt(1));
        } finally {
            try {
                resultSet.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                preparedStatement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    void jdbcContextForUpdate(StatementStrategy statementStrategy) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            //드라이버 로딩
            connection = dataSource.getConnection();
            //sql 작성
            preparedStatement = statementStrategy.makeStatement(connection);
            //sql 실행
            preparedStatement.executeUpdate();
        } finally {
            try {
                preparedStatement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void update(String sql, Object[] params) throws SQLException {
        StatementStrategy statementStrategy = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    sql
            );
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            return preparedStatement;
        };
        jdbcContextForUpdate(statementStrategy);
    }

    void insert(User user, String sql, Object[] params) throws SQLException {
        StatementStrategy statementStrategy = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    sql
                    , Statement.RETURN_GENERATED_KEYS
            );
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            return preparedStatement;
        }; //변하는 것을 추출 Strategy
        jdbcContextForInsert(user, statementStrategy); //변하지 않은 것을 추출 ConText
    }

    User find(String sql, Object[] params) throws SQLException {
        StatementStrategy statementStrategy = connection -> { //Template callback pattern, 람다
            PreparedStatement preparedStatement = connection.prepareStatement(
                    sql
            );
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            return preparedStatement;
        }; //id(dependency)를 생성자로 넘김
        return jdbcContextForFind(statementStrategy);
    }
}