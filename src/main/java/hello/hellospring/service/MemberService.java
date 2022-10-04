package hello.hellospring.service;

import hello.hellospring.domain.member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.Optional;


public class MemberService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    public Long join(member meember) {

        Optional<member> result = memberRepository.findByName(member.getName());
        result.ifPresent(member -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
        memberRepository.save(member);
        return member.getId();
    }
}
