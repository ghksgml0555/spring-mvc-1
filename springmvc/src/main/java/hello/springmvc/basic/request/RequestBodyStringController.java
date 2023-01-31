package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {

    @PostMapping("/request-body-string-v1")
    public void requestBodyString(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //메세지바디의 데이터를 받아서 스트링으로 변환
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}",messageBody);

        response.getWriter().write("ok");
    }

    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException {
        //inputStream, Writer를 바로 받을 수 있다.
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBody={}",messageBody);
        responseWriter.write("ok");
    }

    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) throws IOException {
        //HttpEntity<String>를 보고 스프링이 알아서 메세지바디를 스트링으로 바꿔서 넣어준다.
        //getBody()로 http메세지의 바디를 꺼낼수있다(변환된)
        String messageBody = httpEntity.getBody();
        log.info("messageBody={}",messageBody);

        //바디메세지를 넣을수 있다 > 마치 http메세지를 그대로 주고받는형식으로 만들수있다
        return new HttpEntity<>("ok");
    }

    @ResponseBody //달아주면 반환값을 http응답에 넣어서 반환
    @PostMapping("/request-body-string-v4")
    public String requestBodyStringV4(@RequestBody String messageBody) {
        //@RequestBody 에 String으로 돼있으면 http메세지바디를 읽어서 넣어준다.
        log.info("messageBody={}",messageBody);

        return "ok";
    }
}
