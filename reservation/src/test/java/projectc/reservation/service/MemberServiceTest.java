package projectc.reservation.service;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import projectc.reservation.domain.Member;
import projectc.reservation.repository.MemberRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 회원가입() throws Exception {
        //given
        Member member1 = Member.createMember("hello1234", "katie", 13);

        //when
        memberService.join(member1);
        Member findMember = memberService.findMember("hello1234");

        //then
        Assertions.assertEquals(member1, findMember);
    }

    @Test(expected = IllegalStateException.class)
    public void 회원중복검사() throws Exception {
        //given
        Member member1 = Member.createMember("hello1234", "katie", 13);
        Member member2 = Member.createMember("hello1234", "Chris", 52);

        //when
        memberService.join(member1);
        memberService.join(member2);

        //then
        fail("예외가 발생해야 한다.");
    }
}
