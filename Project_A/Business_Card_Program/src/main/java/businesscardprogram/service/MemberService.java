package businesscardprogram.service;

import businesscardprogram.domain.Member;
import businesscardprogram.repository.MemberRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long create(Member member) {
        // 가장 최근 제작 신청한 사람과 중복되는지 검사
        if (memberRepository.findAll().size() != 0) {
            validateDuplicateMember(member);
        }
        Long savedId = memberRepository.save(member);
        return savedId;
    }

    private void validateDuplicateMember(Member member) {
        Member lastMember = memberRepository.findTop1ByOrderByIdDesc().get(0);
        if (lastMember.getName().equals(member.getName()) && lastMember.getNumber()
            .equals(member.getNumber()) && lastMember.getCompany().equals(member.getCompany())) {
            throw new IllegalStateException("이미 제작 진행 중입니다");
        }
    }

    public List<Member> findAllMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOneById(Long memberId) {
        return memberRepository.findById(memberId);
    }

    public List<Member> findMembersByName(String name) {
        return memberRepository.findByName(name);
    }
}
