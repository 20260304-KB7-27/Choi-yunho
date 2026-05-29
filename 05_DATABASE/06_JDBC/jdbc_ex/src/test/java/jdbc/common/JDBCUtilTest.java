package jdbc.common;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class JDBCUtilTest {
    @Test
    @DisplayName("데이터베이스 접속하기")
    void testConnection() throws ClassNotFoundException, SQLException {

        // Driver 준비
        Class.forName("com.mysql.cj.jdbc.Driver");

        // 접속할 데이터베이스 서버 url, id, pw
        String url = "jdbc:mysql://127.0.0.1:3306/scoula_db";
        String id = "scoula";
        String password = "1234";

        // 커넥션 받아오기
        Connection connection = DriverManager.getConnection(url, id, password);

        System.out.println("DB 연결 성공");
        connection.close();
    }
}