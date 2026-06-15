package scoula.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Person {
    private String name = "Ella";

//    @Autowired
    /*
    * 필드 주입
    * - final 키워드를 쑬 수 없어 불변성 확보가 어려움.
    * */
    private Parrot parrot;

    // 생성자 주입
    // -> Spring 팀에서도 권장하는 방식
    // 순환참조 문제 발견하기 쉬움
    // 생성자가 1개 뿐일 때 @AutoWired 생략 가능
//    @Autowired
    public Person(Parrot parrot) {
        this.parrot = parrot;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Parrot getParrot() {
        return parrot;
    }

//    @Autowired // Setter 주입
//    public void setParrot(Parrot parrot) {
//        this.parrot = parrot;
//    }


}
