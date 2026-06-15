package scoula.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import scoula.beans.Person;
import scoula.config.ProjectConfig;
import scoula.config.ProjectConfig2;

public class Main2 {
    /*
    * 어노테이션 DI 방식 - Setter 주입
    * Setter 메서드 위에 @AutoWired 작성
    * - 객체가 생성된 이후 주입됨.
    * */

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ProjectConfig2.class);

        Person person = context.getBean(Person.class);

        System.out.println("person의 이름 = " + person.getName());
        System.out.println("person의 Parrot = " + person.getParrot().getName());
    }
}
