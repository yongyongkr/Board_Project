package businesscardprogram.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import businesscardprogram.domain.Member;
import businesscardprogram.repository.MemoryMemberRepository;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void createCard() {
        //given
        Member member1 = new Member("철수", "01012345678", "Naver");

        Member member2 = new Member("영희", "01012345678", "Naver");

        Member member3 = new Member("영희", "01012345678", "Kakao");

        //when
        Long saveId = memberService.create(member1);
        memberService.create(member2);
        memberService.create(member3);

        //then
        Member result = memberService.findOne(saveId).orElse(null);
        assertThat(member1).isEqualTo(result);
    }

    @Test
    void checkDuplicate() {
        //given
        Member member1 = new Member("철수", "01012345678", "Naver");

        Member member2 = new Member("영희", "01012345678", "Kakao");

        Member member3 = new Member("철수", "01012345678", "Naver");

        //when
        memberService.create(member1);
        memberService.create(member2);
        IllegalStateException e = assertThrows(IllegalStateException.class,
            () -> memberService.create(member3));
        assertThat(e.getMessage()).isEqualTo("이미 제작 진행 중입니다");
    }

    @Test
    void findMembers() {
        //given
        Member member1 = new Member("철수", "01012345678", "Naver");

        Member member2 = new Member("영희", "01012345678", "Kakao");

        Member member3 = new Member("철수", "01012345678", "Kakao");

        //when
        memberService.create(member1);
        memberService.create(member2);
        memberService.create(member3);

        //then
        List<Member> result = memberService.findMembers();

        assertThat(result.size()).isEqualTo(3);
    }
}