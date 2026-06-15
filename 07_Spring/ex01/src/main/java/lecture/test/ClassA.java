package lecture.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClassA {

//    @Autowired
//    private ClassB classB;

    private ClassB classB;

    @Autowired
    public ClassA(ClassB classB) {
        this.classB = classB;
    }

    public void doSomething() {
        System.out.println("Class A is Working");
        classB.doSomething();
    }
}
