package org.scoula.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RootConfig.class})
@Log4j2
class RootConfigTest {

    @Autowired
    private DataSource dataSource; // hikariDataSource

    @Test
    public void testPool() throws SQLException {

        HikariDataSource hikariDataSource = (HikariDataSource) dataSource;
        var pool = hikariDataSource.getHikariPoolMXBean();

        int minIdle = hikariDataSource.getMinimumIdle();
        int maxPool = hikariDataSource.getMaximumPoolSize();
        long timeoutMs = hikariDataSource.getConnectionTimeout();

        log.info("minIdle : {}", minIdle);
        log.info("maxPool : {}", maxPool);
        log.info("timeoutMs : {}", timeoutMs);

        log.info("[시작] total=" + pool.getTotalConnections() // 총 Connection 갯수
                + ", active=" + pool.getActiveConnections()  // 사용중인 Connection 갯수
                + ", idle=" + pool.getIdleConnections());    // 대기중인 Connection 갯수

        List<Connection> held = new ArrayList<>();
        for (int i = 1; i <= maxPool; i++) {
            held.add(dataSource.getConnection());
            log.info("[" + i + "개 빌림] total=" + pool.getTotalConnections()
                    + ", active=" + pool.getActiveConnections()
                    + ", idle=" + pool.getIdleConnections()
                    + (pool.getTotalConnections() > minIdle ? "  <- minIdle 초과! 풀이 늘어남" : ""));
        }

        log.info("[최대 도달] total=" + pool.getTotalConnections()
                + " (설정한 maximumPoolSize=" + maxPool + " 와 같아야 함)");
    }

}