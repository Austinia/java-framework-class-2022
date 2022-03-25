package kr.ac.jejunu;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;

public class UserDaoTests {
    //테스트 할 것
    @Test
    public void findById() throws SQLException, ClassNotFoundException {
        Integer id = 1;
        String name = "hulk";
        String password = "1234";

        UserDao userDao = new JejuUserDao();
        User user = userDao.findById(id); //그릇
        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
        //이제부터 빨간색을 지우자
    }

    @Test
    public void insert() throws SQLException, ClassNotFoundException {
        String pasaword = "1111";
        String name = "hulk";

        User user = new User();
        user.setName(name);
        user.setPassword(pasaword);

        UserDao userDao = new JejuUserDao();
        userDao.insert(user); // name, password를 넣고 자동으로 만들어진 id도 가져오자.

        assertThat(user.getId(), greaterThan(0)); // 0보다 큰 것?

        User insertedUser = userDao.findById(user.getId());
        assertThat(insertedUser.getName(), is(user.getName()));
        assertThat(insertedUser.getPassword(), is(user.getPassword()));
    }

    //테스트 할 것
    @Test
    public void findByIdForHalla() throws SQLException, ClassNotFoundException {
        Integer id = 1;
        String name = "hulk";
        String password = "1234";

        UserDao userDao = new HallaUserDao();
        User user = userDao.findById(id); //그릇
        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
        //이제부터 빨간색을 지우자
    }

    @Test
    public void insertForHalla() throws SQLException, ClassNotFoundException {
        String pasaword = "1111";
        String name = "hulk";

        User user = new User();
        user.setName(name);
        user.setPassword(pasaword);

        UserDao userDao = new HallaUserDao();
        userDao.insert(user); // name, password를 넣고 자동으로 만들어진 id도 가져오자.

        assertThat(user.getId(), greaterThan(0)); // 0보다 큰 것?

        User insertedUser = userDao.findById(user.getId());
        assertThat(insertedUser.getName(), is(user.getName()));
        assertThat(insertedUser.getPassword(), is(user.getPassword()));
    }
}
