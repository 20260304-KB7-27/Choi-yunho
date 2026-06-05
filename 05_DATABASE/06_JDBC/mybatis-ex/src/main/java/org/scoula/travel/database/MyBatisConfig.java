package org.scoula.travel.database;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;

/*
* SQLSessionFactory
* - mybatis-config.xml 설정을 바탕으로 SQLSession을 만드는 객체
* - 생성 비용이 크기 때문에 어플리케이션에서 하나만 만들고 사용 (싱글톤)
* */
public class MyBatisConfig {
    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // openSession()으로 만들어진 Session은 AutoCommit = False 상태
    public static SqlSession getSqlSession() {
        return sqlSessionFactory.openSession(); // 새로운 SqlSession의 인스턴스 리턴
    }
}