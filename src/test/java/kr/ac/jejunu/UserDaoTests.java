package kr.ac.jejunu;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;

public class UserDaoTests {
    private static UserDao userDao; // 두 개의 메소드에서 쓸 수 있도록 스태틱으로 만들기

    @BeforeAll
    public static void setup() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DaoFactory.class); //빈을 관리해주는 코어 클래스 AC, DaoFactory가 아니라 스프링에게 달라고 하기
        userDao = applicationContext.getBean("userDao", UserDao.class); //userDao는 이제 인스턴스를 갖는다.
    }

    //테스트 할 것
    @Test
    public void findById() throws SQLException, ClassNotFoundException {
        Integer id = 1;
        String name = "hulk";
        String password = "1234";
//        DaoFactory daoFactory = new DaoFactory();
//        UserDao userDao = daoFactory.userDao(); //내가 왜 뭔지 NEW를 해줘야 해(의존성 갖기 싫어)
        User user = userDao.findById(id); //그릇
        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
        //이제부터 빨간색을 지우자
    }

    @Test
    public void insert() throws SQLException, ClassNotFoundException {
        String password = "1111";
        String name = "hulk";

        User user = new User();
        user.setName(name);
        user.setPassword(password);
//        DaoFactory daoFactory = new DaoFactory();
//        UserDao userDao = daoFactory.userDao(); //내가 왜 뭔지 NEW를 해줘야 해(의존성 갖기 싫어)
        userDao.insert(user); // name, password를 넣고 자동으로 만들어진 id도 가져오자.

        assertThat(user.getId(), greaterThan(0)); // 0보다 큰 것?

        User insertedUser = userDao.findById(user.getId());
        assertThat(insertedUser.getName(), is(user.getName()));
        assertThat(insertedUser.getPassword(), is(user.getPassword()));
    }

    @Test
    public void update() throws SQLException, ClassNotFoundException {
        //사용자 추가
        User user = new User();
        user.setPassword("4321");
        user.setName("kluh");
        userDao.insert(user);
        //수정
        String updatedName = "허윤효";
        String updatedPassword = "5315";

        user.setName(updatedName);
        user.setPassword(updatedPassword);

        userDao.update(user);
        //확인
        User updatedUser = userDao.findById(user.getId());

        assertThat(updatedUser.getName(), is(updatedName));
        assertThat(updatedUser.getPassword(), is(updatedPassword));
    }

    @Test
    public void delete() throws SQLException, ClassNotFoundException {
        //사용자 추가
        User user = new User();
        user.setPassword("2314");
        user.setName("kiki");
        userDao.insert(user);
        //삭제
        userDao.delete(user.getId());
        //확인
        User deletedUser = userDao.findById(user.getId());
        assertThat(deletedUser, nullValue());
    }
}
