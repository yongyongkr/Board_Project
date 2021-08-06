package projectc.reservation.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projectc.reservation.domain.Member;
import projectc.reservation.repository.MemberRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public String join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        Member findMember = memberRepository.findOne(member.getId());
        if (findMember != null) {
            throw new IllegalStateException("이미 존재하는 회원 아이디입니다.");
        }
    }

    public List<Member> findAllMembers() {
        return memberRepository.findAll();
    }

    public Member findMember(String memberId) {
        return memberRepository.findOne(memberId);
    }
}
