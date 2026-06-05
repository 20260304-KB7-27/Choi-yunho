package org.scoula.travel.database;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyBatisConfigTest {

    @Test
    void testConnection() {

        assertNotNull(MyBatisConfig.getSqlSession(), "객체가 만들어지나?");
    }
}z