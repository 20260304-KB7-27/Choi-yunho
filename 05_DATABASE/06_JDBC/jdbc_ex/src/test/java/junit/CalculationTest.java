package junit;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class CalculationTest {

    @Test
    void add() {
        // given : 테스트에 필요한 준비
        Calculation calculation = new Calculation();

        // when
        int result = calculation.add(2, 3);

        // then
        assertEquals(5, result);
    }

    @Test
    void assertJExample() {
        String text = "Hello, AssertJ";

        // 문자열
        assertThat(text)
                .isNotEmpty()
                .startsWith("Hello")
                .endsWith("J")
                .contains(",");

    }
}