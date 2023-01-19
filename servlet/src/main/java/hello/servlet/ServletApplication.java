package hello.servlet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan /* 스프링 부트에서 서블릿을 쓰려면 이 애노테이션을 붙혀주면
                      스프링이 자동으로 현재 내 패키지를 포함해서 하위패키지를 다 뒤져서
                      서블릿을 다 찾아서 자동으로 서블릿을 등록해서 실행할 수 있도록 해준다.
                       ==> 서블릿 자동등록 */
@SpringBootApplication
public class ServletApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServletApplication.class, args);
	}
}
