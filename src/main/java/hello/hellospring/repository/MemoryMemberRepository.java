package hello.hellospring.repository;

import hello.hellospring.domain.member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public member save(member memer) {
        return member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<member> findById(Long id) {

        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<member> findByName(String name) {
        store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<member> findAll() {

        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
