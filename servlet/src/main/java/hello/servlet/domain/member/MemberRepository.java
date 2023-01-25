package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;
    //static으로 했기때문에 아무리 MemberRepository가 new로 많아져도 위에 애들은 1개만 사용
    //사실 아래에 싱글톤으로 해둬서 static없어도 하나인게 보장이 되긴 함

    //스프링을 안쓸거라서 싱글톤으로 만들기 > 스프링을 쓰면 스프링에서 싱글톤을 보장해줘서
    //싱글톤을 쓸필요가 없다
    private static final MemberRepository instance = new MemberRepository();

    public static MemberRepository getInstance(){
        return instance;
    }

    private MemberRepository(){ //생성자 막기
    }

    public Member save(Member member){
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id){
        return store.get(id);
    }

    public List<Member> findAll(){
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
