package hello.hellospring.repository;

import hello.hellospring.domain.member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        repository.clearStore();

    }
    @Test
    public void save(){
        member member = new member();
        member.setName("spring");

        repository.save(member);

        member result = repository.findById(member.getId()).get();
        assertThat(member).isEqualTo(result);

    }
    @Test
    public void findByName() {
        member member1 = new member();
        member1.setName("spring1");
        repository.save(member1);

        member member2 = new member();
        member2.setName("spring2");
        repository.save(member2);

        member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        member member1 = new member();
        member1.setName("spring1");
        repository.save(member1);

        member member2 = new member();
        member2.setName("spring2");
        repository.save(member2);

        List<member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
}
}