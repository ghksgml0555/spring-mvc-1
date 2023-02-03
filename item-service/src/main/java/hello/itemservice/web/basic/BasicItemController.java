package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item",item);
        return "basic/item";
    }

    @GetMapping("/add") //상품등록 폼 호출
    public String addForm(){
        return "basic/addForm";
    }

  //  @PostMapping("/add")
    public String addItemV1(@RequestParam String itemName, //addForm의 input의 name으로 넘어온다.
                       @RequestParam int price,
                       @RequestParam Integer quantity,
                       Model model) {
        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);

        itemRepository.save(item);

        model.addAttribute("item",item);

        return "basic/item";
    }

   // @PostMapping("/add")
    public String addItemV2(@ModelAttribute("item")Item item, Model model) {
        /*Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);
        위 내용을 @ModelAttribute가 자동으로 만들어줘서 없애도 된다.
        */
        itemRepository.save(item);

        model.addAttribute("item",item);

        return "basic/item";
    }

   // @PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item) {
        //@ModelAttribute의 룰 > 클래스명인 Item을 첫글자만 소문자로 바꿔서
        //item을 name속성으로 모델에 넣는다.
        itemRepository.save(item);
        return "basic/item";
    }

    //@PostMapping("/add")
    public String addItemV4(Item item) {
        itemRepository.save(item);
        return "basic/item";
    }

   // @PostMapping("/add")
    public String addItemV5(Item item) {
        itemRepository.save(item);
        return "redirect:/basic/items/" + item.getId();
    }

    @PostMapping("/add")
    public String addItemV6(Item item, RedirectAttributes redirectAttributes) {
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId",savedItem.getId());
        redirectAttributes.addAttribute("status",true);
        //위처럼 해두면 아래처럼 {itemId}를 쓸 수 있다, url인코딩도 해결된다.
        //남은 status는 쿼리파라미터처럼 합쳐진다.
        return "redirect:/basic/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item",item);
        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item){
        itemRepository.update(itemId, item);
        //리다이렉트로 상품상세로 다시보내기
        return "redirect:/basic/items/{itemId}";
    }



    //테스트용 데이터 추가
    @PostConstruct
    public void init(){
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));
    }
}
