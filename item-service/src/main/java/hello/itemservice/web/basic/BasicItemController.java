package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;

    /*@Autowired //BasicItemController가 컨트롤러로 스브링빈에 등록되면서 생성자 주입으로 itemRepository가 주입된다.
    public BasicItemController(ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }
     >> 스프링에서 생성자가 하나뿐이면 @Autowired를 지울 수 있다.
     >>lombok에 @RequiredArgsConstructor 를 달아주면 위 생성자에서 @Autowired가 없는 버전의 생성자를 자동으로 만들어준다
    >>다지우고 @RequiredArgsConstructor만 달아주면 위 생성자와 같은 효과
     */

    @GetMapping
    public String items(Model model){
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items",items);
        return "basic/items";
    }

    //테스트용 데이터 추가
    @PostConstruct
    public void init(){
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));
    }
}
