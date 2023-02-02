package hello.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach//각테스트가 끝날때마다 얘가 실행된다
    void afterEach(){
        itemRepository.clearStore();
    }

    @Test
    void save(){ //junit5부터는 public없어도 된다.
        //given
        Item item = new Item("itemA",10000,10);
        //when
        Item savedItem = itemRepository.save(item);
        //then
        Item findItem = itemRepository.findById(item.getId());
        Assertions.assertThat(findItem).isEqualTo(savedItem);
    }

    @Test
    void findAll(){ //junit5부터는 public없어도 된다.
        //given
        Item item1 = new Item("item1",10000,10);
        Item item2 = new Item("item2",20000,20);

        itemRepository.save(item1);
        itemRepository.save(item2);

        //when
        List<Item> result = itemRepository.findAll();

        //then
        Assertions.assertThat(result.size()).isEqualTo(2);
        Assertions.assertThat(result).contains(item1, item2); //item1, item2를 가지고 있는지

    }

    @Test
    void updateItem(){ //junit5부터는 public없어도 된다.
        //given
        Item item = new Item("item1",10000,10);

        Item savedItem = itemRepository.save(item);
        Long itemId = savedItem.getId();

        //when
        Item updateParam = new Item("item2",20000,20);
        itemRepository.update(itemId, updateParam);
        //then
        Item findItem = itemRepository.findById(itemId);

        Assertions.assertThat(findItem.getItemName()).isEqualTo(updateParam.getItemName());
        Assertions.assertThat(findItem.getPrice()).isEqualTo(updateParam.getPrice());
        Assertions.assertThat(findItem.getQuantity()).isEqualTo(updateParam.getQuantity());

    }
}
