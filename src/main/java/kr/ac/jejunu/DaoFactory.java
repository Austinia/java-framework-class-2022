package kr.ac.jejunu; //의존성은 여기에 다 모여서 얘가 모든 의존성을 다 다뤘으면 좋겠네 new를 내가 다 한다!

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.sql.Driver;

@Configuration //설정하겠다
public class DaoFactory {
    @Value("${db.drivername}") //인펜던시를 환경변수에 던진다. 환경변수는 설정에 있음
    private String driverClassName; // "com.mysql.cj.jdbc.Driver";
    @Value("${db.url}")
    private String url; // "jdbc:mysql://localhost:3306/jejunu";
    @Value("${db.username}")
    private String username; // "jeju";
    @Value("${db.password}")
    private String password; // "jeju";

    @Bean //스프링에서 대신 뉴해주고 관리해주는 콩
    public UserDao userDao() throws ClassNotFoundException {
        return new UserDao(dataSource());
    } //?

    @Bean
    public DataSource dataSource() throws ClassNotFoundException {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(
                (Class<? extends Driver>) Class.forName(driverClassName));
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }
}
