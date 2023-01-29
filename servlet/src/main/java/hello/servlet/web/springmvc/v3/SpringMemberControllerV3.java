package hello.servlet.web.springmvc.v3;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    /*@RequestMapping("/new-form")
    public ModelAndView newForm(){
        return new ModelAndView("new-form");
    }아래처럼 바꿔도 정상적으로 동작함 >애노테이션 기반의 컨트롤러는 모델엔뷰 를 반환해도되고
    문자를 반환해도 된다. > 문자를 반환하면 그 문자를 뷰이름으로해서 진행*/
    //@RequestMapping(value = "/new-form", method = RequestMethod.GET)
    @GetMapping("new-form")
    public String newForm(){
        return "new-form";
    }

    /*@RequestMapping("/save")
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        ModelAndView mv = new ModelAndView("save-result");
        // mv.getModel().put("member",member);
        //위와 아래가 같은동작이다. ModelAndView의 메서드
        mv.addObject("member",member);
        return mv;
    }@RequestParam으로 파라미터이름을 넣어줘서 파라미터를 직접 받을 수 있다.
    int age > 타입캐스팅도 자동으로 처리해준다.*/
    //@RequestMapping(value = "/save",method = RequestMethod.POST)
    @PostMapping("save")
    public String save(@RequestParam("username")String username,
                             @RequestParam("age")int age,
                             Model model) {

        Member member = new Member(username, age);
        memberRepository.save(member);

        model.addAttribute("member",member);
        return "save-result";
    }

    /*@RequestMapping
    public ModelAndView members() {
        List<Member> members = memberRepository.findAll();
        ModelAndView mv = new ModelAndView("members");
        mv.addObject("members",members);

        return mv;
    }*/
    //@RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public String members(Model model) {
        List<Member> members = memberRepository.findAll();

        model.addAttribute("members",members);
        return "members";
    }
}
