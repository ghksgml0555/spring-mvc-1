package hello.servlet.basic;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter //lombok
public class HelloData {

    private String username;
    private int age;

    /*제이슨 라이브러리가 세터를 기본으로 호출한다.
    꺼내는건 우리가 필요하면 게터로 꺼낼 수 있다.
    lombok이 있어서 위에 @Getter @Setter로 아래 게터, 세터가 자동으로 들어간다.
    public String getUsername() {
        return username;
    }
    public int getAge() {
        return age;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setAge(int age) {
        this.age = age;
    }
     */
}
