package junit;

import org.junit.jupiter.api.*;

// 실행 순서 지정
// MethodOrderer.OrderAnnotation.class -> @Order 값 기준으로 순서 결정
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LifecycleTest {

    // DB 연결 같은 전체 테스트에서 공통으로 필요한 자원들을 초기화할 때 (static 필수)
    @BeforeAll
    static void beforeAll() {
        System.out.println("beforeAll : 모든 테스트 전 최초 1회 실행");
    }
    @AfterAll // 사용한 자원을 반환하거나 @BeforeAll에서 열어둔 자원 정리
    static void AfterAll() {
        System.out.println("a   fterAll : 모든 테스트 실행 후 최종 1회 실행");
    }

    // 테스트 마다 독립적인 초기 상태 보장이 필요할 때
    @BeforeEach
    void beforeEach() {
        System.out.println("beforeEach : 각 테스트 전 실행");
    }

    @AfterEach
    void AfterEach() {
        System.out.println("afterEach : 각 테스트 후 실행");
    }

    @Test
    @Order(3) // 실행 순서 지정 (숫자가 낮을 수록 먼저 실행)
    void test1() {
        System.out.println("test : 테스트 1 실행");
    }

    @Test
    @Order(2)
    void test2() {
        System.out.println("test : 테스트 2 실행");
    }

    @Test
    @Order(1)
    void test3() {
        System.out.println("test : 테스트 3 실행");
    }
 }
