package hello.springmvc.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogTestController {
    //Logger는 org.slf4g 패키지를 선택해야한다
    private final Logger log = LoggerFactory.getLogger(LogTestController.class);

    @RequestMapping("/log-test")
    public String logTest(){
        String name="Spring";

        System.out.println("name = " + name);

        log.trace(" trace log ={}", name);
        log.debug(" debug log ={}", name);
        log.info(" info log ={}", name);
        log.warn(" warn log ={}", name);
        log.error(" error log ={}", name);

        return "ok";
    }
}
