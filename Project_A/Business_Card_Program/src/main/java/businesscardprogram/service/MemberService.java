package businesscardprogram.service;

import businesscardprogram.domain.Member;
import businesscardprogram.repository.MemberRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //회원 가입
    public Long create(Member member) {
        //같은 이름, 전화번호, 회사를 가진 중복 회원 X
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
            .filter(m -> m.getNumber().equals(member.getNumber()) && m.getCompany()
                .equals(member.getCompany()))
            .ifPresent(m -> {
                throw new IllegalStateException("이미 제작 진행 중입니다");
            });
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
