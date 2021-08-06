package projectc.reservation.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import projectc.reservation.domain.Member;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 회원가입() throws Exception {
        //given
        Member member1 = Member.createMember("hello123", "Brian", 24);

        //when
        String savedId = memberRepository.save(member1);
        Member findMember = memberRepository.findOne(savedId);

        //then
        assertThat(findMember.getId()).isEqualTo(member1.getId());
        assertThat(findMember.getName()).isEqualTo(member1.getName());
        assertThat(findMember.getAge()).isEqualTo(member1.getAge());
    }

    @Test
    public void 회원찾기() throws Exception {
        //given
        Member member1 = Member.createMember("hello123", "Brian", 24);
        memberRepository.save(member1);

        //when
        Member findMember = memberRepository.findOne("hello123");

        //then
        assertEquals(findMember.getName(), "Brian");
        assertEquals(findMember.getAge(), 24);
    }

    @Test
    public void 회원전체조회() throws Exception {
        //given
        Member member1 = Member.createMember("hello123", "Brian", 24);
        Member member2 = Member.createMember("hello312", "Suzan", 52);

        //when
        memberRepository.save(member1);
        memberRepository.save(member2);

        //then
        assertThat(memberRepository.findAll().size()).isEqualTo(2);
    }
}