package hello.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello")
//@WebServlet : 서블릿 애노테이션, name : 서블릿 이름, urlPatterns: URL매핑
//이름을주고 , /hello로 오면 이게 실행된다.
public class HelloServlet extends HttpServlet {//서블릿은 HttpServlet을 상속받음

    //컨트롤 + O로 service 찾아서 좌물쇠 잠긴게 protected
    //이렇게 해두면 서블릿이 호출되면 서비스 메소드가 호출된다.
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("HelloServlet.service");
        System.out.println("request = " + request);
        System.out.println("response = " + response);

        //서블릿은 쿼리파라미터를 getParameter로 편하게 읽을 수 있게 해준다.
        String username = request.getParameter("username");
        System.out.println("username = " + username);

        response.setContentType("text/plain"); //단순문자를 보낼것
        response.setCharacterEncoding("utf-8");//문자세트 인코딩 정보 > 요즘은 utf-8
        response.getWriter().write("hello " + username); //http메세지 바디에 데이터가 들어간다.
    }
}
