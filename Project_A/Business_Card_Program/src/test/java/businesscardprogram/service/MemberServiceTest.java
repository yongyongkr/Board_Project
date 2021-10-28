package businesscardprogram.service;

import static org.assertj.core.api.Assertions.*;

import businesscardprogram.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired MemberService memberService;

    @Test
    public void 명함생성() throws Exception {
        //given
        Member member = Member.createMember("Peter", "010-4218-5236", "Samsung");

        //when
        Long memberId = memberService.create(member);

        //then
        assertThat(memberService.findOneById(memberId).get().getName()).isEqualTo("Peter");
        assertThat(memberService.findAllMembers().size()).isEqualTo(1);
    }

    @Test(expected = IllegalStateException.class)
    public void 중복예외검사() throws Exception {
        //given
        Member member1 = Member.createMember("Peter", "010-4218-5236", "Samsung");
        Member member2 = Member.createMember("Katie", "010-4543-2326", "Kakao");
        Member member3 = Member.createMember("Katie", "010-4543-2326", "Kakao");

        //when
        memberService.create(member1);
        memberService.create(member2);

        //then
        memberService.create(member3);
    }

    @Test
    public void 전체조회() throws Exception {
        //given
        Member member1 = Member.createMember("Peter", "010-4218-5236", "Samsung");
        Member member2 = Member.createMember("Katie", "010-4543-2326", "Kakao");

        //when
        memberService.create(member1);
        memberService.create(member2);

        //then
        assertThat(memberService.findAllMembers().size()).isEqualTo(2);
    }
    
    @Test
    public void 아이디로찾기() throws Exception {
        //given
        Member member1 = Member.createMember("Peter", "010-4218-5236", "Samsung");
        Member member2 = Member.createMember("Katie", "010-4543-2326", "Kakao");
        
        //when
        memberService.create(member1);
        Long findMemberId = memberService.create(member2);

        //then
        assertThat(memberService.findOneById(findMemberId).get().getName()).isEqualTo(
            member2.getName());
    }
    
    @Test
    public void 이름으로조회() throws Exception {
        //given
        Member member1 = Member.createMember("Peter", "010-4218-5236", "Samsung");
        Member member2 = Member.createMember("Katie", "010-4543-2326", "Kakao");
        Member member3 = Member.createMember("Peter", "010-2324-6443", "Naver");

        //when
        memberService.create(member1);
        memberService.create(member2);
        memberService.create(member3);

        //then
        assertThat(memberService.findMembersByName("Peter").size()).isEqualTo(2);
    }
}