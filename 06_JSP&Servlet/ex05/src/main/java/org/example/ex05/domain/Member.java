package org.example.ex05.domain;

// 기본생성자
// 모든필드 초기화하는 생성자
// Getter / Setter 만들기
public class Member {

    private String name;
    private String userid;

    public Member() {
    }

    public Member(String name, String userid) {
        this.name = name;
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
