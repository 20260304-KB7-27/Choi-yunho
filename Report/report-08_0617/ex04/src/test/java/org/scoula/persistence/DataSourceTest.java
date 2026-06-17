package org.scoula.persistence;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.scoula.config.RootConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RootConfig.class})
@Log4j2
public class DataSourceTest {

    @Autowired
    private DataSource dataSource;

    @Test
    @DisplayName("DataSource 빈 생성이 정상적으로 수행된다.")
    public void testConnection() {
        assertNotNull(dataSource);
        try (Connection con = dataSource.getConnection()) {
            log.info("DataSource를 통한 Connection 획득 성공: " + con);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}
