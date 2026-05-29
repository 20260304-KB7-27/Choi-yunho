package jdbc.section01;

import jdbc.common.JDBCUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Application1 {

    /*
     * Statement
     * - JDBC에서 SQL 문을 사용하기 위한 interface
     * - SQL문을 문자열 그대로 직접 작성해서 실행
     * */
    public static void main(String[] args) {

        // Connection
        Connection con = JDBCUtil.getConnection();

        // java.sql의 interface로 import
        Statement stmt = null;

        // 결과집합 (Select) 인터페이스
        ResultSet rset = null;

        try {
            stmt = con.createStatement();

            // SQL Injection
            String userInput = "' OR '1' = '1";

            String query = "SELECT * FROM usertbl WHERE name = '" + userInput + "'";
            rset = stmt.executeQuery(query);

            System.out.println("query : " + query);

            while (rset.next()) {
                System.out.println(
                        rset.getString("id") + ", "
                                + rset.getString("name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close();
        }

    }
}
