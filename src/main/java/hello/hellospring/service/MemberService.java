package hello.hellospring.service;

import hello.hellospring.domain.member;
import hello.hellospring.repository.MemberRepository;

import java.util.List;
import java.util.Optional;


public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Long join(member meember) {

        validateDuplicateMember(member);
        memberRepository.save(member, parameterIndex);
        return member.getId();
    }

    private void validateDuplicateMember(member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(member -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }
    public List<member> findMembers() {
        return memberRepository.findAll();
    }
    public Optional<member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
