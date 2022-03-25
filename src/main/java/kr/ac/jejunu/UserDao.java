package kr.ac.jejunu;

import java.sql.*;

public class UserDao {
    private final ConnectionMaker connectionMaker; //인터페이스를 쓸건데

    public UserDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker; //의존성은 만드는 녀석들에게 맡길게
    }

    public User findById(Integer id) throws SQLException, ClassNotFoundException {
        Connection connection = connectionMaker.getConnection();
        //sql 작성
        PreparedStatement preparedStatement = connection.prepareStatement("select * from userinfo where id = ?");
        preparedStatement.setInt(1, id);
        //sql 실행
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        //User 에 데이터 매핑
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setName(resultSet.getString("name"));
        user.setPassword(resultSet.getString("password"));
        //자원 해지
        resultSet.close();
        preparedStatement.close();
        connection.close();
        //User 리턴
        return user;
    }

    public void insert(User user) throws ClassNotFoundException, SQLException {
        Connection connection = connectionMaker.getConnection();
        //sql 작성
        PreparedStatement preparedStatement = connection.prepareStatement("insert into userinfo(name, password) values (?, ?)",
                Statement.RETURN_GENERATED_KEYS); // DB에서 시퀀스로 만들어낸 데이터를 가져오는 기능
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getPassword());
        //sql 실행
        preparedStatement.executeUpdate();
        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        resultSet.next();
        //User 에 데이터 매핑
        user.setId(resultSet.getInt(1));
        //자원 해지
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException { //Refactor delegate
        return connectionMaker.getConnection();
    };
}
