package kr.ac.jejunu; //의존성은 여기에 다 모여서 얘가 모든 의존성을 다 다뤘으면 좋겠네 new를 내가 다 한다!

public class DaoFactory {
    public UserDao getUserDao() {
        return new UserDao(getConnectionMaker());
    }

    private ConnectionMaker getConnectionMaker() {
        return new JejuConnectionMaker();
    }
}
