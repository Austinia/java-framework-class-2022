package kr.ac.jejunu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface ConnectionMaker { //클래스 레벨의 추상화는 인터페이스
    Connection getConnection() throws ClassNotFoundException, SQLException;
}