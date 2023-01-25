package hello.servlet.basic.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name= "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ///[status-line]
        //응답코드 설정은 setStatus()
        //SC_OK부분에 컨트롤을 누르고 마우스를 올린뒤 클릭하면 정의된 상수를 볼 수 있다. SC_OK > 200
        response.setStatus(HttpServletResponse.SC_OK);

        ///[response-headers]
        //헤더들의 데이터 세팅
        response.setHeader("Content-type", "text/plain;charset-utf-8");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma","no-cache");
        //내가 원하는 임의의 헤더도 만들 수 있다
        response.setHeader("my-header","hello");

        ///[Header 편의 메서드] > 위와 똑같은 기능이라서 주석처리
        //content(response);

        ///[쿠키 편의 메서드]
        cookie(response);

        ///[리다이렉트 편의 메서드]
        redirect(response);

        response.getWriter().write("ok");
    }

    private void content(HttpServletResponse response) {
        //Content-Type: text/plain;charset=utf-8
        //Content-Length: 2
        //아래처럼 setHeader 하지 않고 setContentType이 가능하다.
        //response.setHeader("Content-Type", "text/plain;charset=utf-8");
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        //response.setContentLength(2); //(생략시 자동 생성) > ok라서 2
    }

    private void cookie(HttpServletResponse response) {
        //아래처럼 할 수 있지만 번거롭기에 해당 기능이 있다.
        //response.setHeader("Set-Cookie", "myCookie=good; Max-Age=600");
        Cookie cookie = new Cookie("myCookie", "good");
        cookie.setMaxAge(600); //600초
        response.addCookie(cookie);
    }

    private void redirect(HttpServletResponse response) throws IOException {
        //response.setStatus(HttpServletResponse.SC_FOUND); //302
        //response.setHeader("Location", "/basic/hello-form.html");
        response.sendRedirect("/basic/hello-form.html");
    }
}
