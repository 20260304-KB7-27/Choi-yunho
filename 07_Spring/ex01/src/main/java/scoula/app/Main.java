package scoula.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import scoula.config.ProjectConfig;
import scoula.domain.Parrot;

public class Main {
    /*
    * IOC (Inversion Of Control) 제어의 역전
    * 1. 의존성 자동 주입 (DI) -> 필요한 객체를 Spring이 주입해줌.
    * 2. 낮은 결합도 -> 클래스간 강한 연결없이 유지보수하기 좋은 구조
    * 3. Bean 생명주기 관리 -> 싱글톤
    * */

    public static void main(String[] args) {
        Parrot p = new Parrot();
        System.out.println("self : " + p);

        ApplicationContext context = new AnnotationConfigApplicationContext(ProjectConfig.class);

        Parrot p2 = context.getBean(Parrot.class); // Parrot.class 형태로 되어있는 Bean 가져오기
        System.out.println("p2 = " + p2);

        Parrot p3 = context.getBean(Parrot.class); // Parrot.class 형태로 되어있는 Bean 가져오기
        System.out.println("p3 = " + p3);

        // p2, p3는 같은 Spring 객체(등록된 Bean)로 같은 값이 출력
        // p1은 다른 값 출력
    }
}
