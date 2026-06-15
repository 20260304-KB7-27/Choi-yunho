package scoula.beans;

import org.springframework.stereotype.Component;

@Component
public class Parrot {
    private String name = "bean 등록된 parrot";

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
