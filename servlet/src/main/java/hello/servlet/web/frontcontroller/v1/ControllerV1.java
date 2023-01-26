package hello.servlet.web.frontcontroller.v1;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ControllerV1 { //이걸로 구현을 여러개 해서 회원폼, 회원저장, 회원리스트 컨트롤러들을 만든다.
    //서블릿이랑 똑같은 모양의 인터페이스를 만든다.
    void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
