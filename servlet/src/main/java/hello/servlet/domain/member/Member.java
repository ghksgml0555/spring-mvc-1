package hello.servlet.domain.member;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Member {

    private Long id; //식별자 id값
    private String username;
    private int age;

    public Member(){ //기본생성자
    }

    public Member(String username, int age){
        this.username = username;
        this.age = age;
    }
}
