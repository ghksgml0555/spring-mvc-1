package hello.servlet.web.frontcontroller.v1.controller;

import hello.servlet.web.frontcontroller.v1.ControllerV1;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberFormControllerV1 implements ControllerV1 {

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String viewPath = "/WEB-INF/views/new-form.jsp";
        //getRequestDispatcher() > 컨트롤러에서 뷰로 이동할때 사용하는것
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        //dispatcher.forward를 호출하면 서블릿에서 JSP를 호출하게된다.
        dispatcher.forward(request, response);
    }
}
