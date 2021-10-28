package businesscardprogram.repository;

import static org.assertj.core.api.Assertions.assertThat;

import businesscardprogram.domain.Member;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class JpaMemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 저장() throws Exception {
        //given
        Member member = Member.createMember("Peter", "010-4218-5236", "Samsung");

        //when
        memberRepository.save(member);

        //then
        assertThat(memberRepository.findAll().size()).isEqualTo(1);
    }

    @Test
    public void 아이디로_조회() throws Exception {
        //given
        Member member1 = Member.createMember("Peter", "010-4218-5236", "Samsung");
        Member member2 = Member.createMember("Katie", "010-4543-2326", "Kakao");

        //when
        memberRepository.save(member1);
        memberRepository.save(member2);

        //then
        Long findMemberId = member1.getId();
        assertThat(memberRepository.findById(findMemberId).get().getName()).isEqualTo(
            member1.getName());
    }

    @Test
    public void 이름으로_조회() throws Exception {
        //given
        Member member1 = Member.createMember("Peter", "010-4218-5236", "Samsung");
        Member member2 = Member.createMember("Peter", "010-3228-5366", "Naver");
        Member member3 = Member.createMember("Katie", "010-4543-2326", "Kakao");

        //when
        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);

        //then
        assertThat(memberRepository.findByName("Peter").size()).isEqualTo(2);
    }

    @Test
    public void 전체조회() throws Exception {
        //given
        Member member1 = Member.createMember("Peter", "010-4218-5236", "Samsung");
        Member member2 = Member.createMember("Peter", "010-3228-5366", "Naver");
        Member member3 = Member.createMember("Katie", "010-4543-2326", "Kakao");

        //when
        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);

        //then
        assertThat(memberRepository.findAll().size()).isEqualTo(3);
    }

    @Test
    public void 마지막_아이디_조회() throws Exception {
        //given
        Member member1 = Member.createMember("Peter", "010-4218-5236", "Samsung");
        Member member2 = Member.createMember("Peter", "010-3228-5366", "Naver");
        Member member3 = Member.createMember("Katie", "010-4543-2326", "Kakao");

        //when
        memberRepository.save(member1);
        memberRepository.save(member2);
        Long lastId = memberRepository.save(member3);

        //then
        Member lastMember = memberRepository.findTop1ByOrderByIdDesc().get(0);
        assertThat(lastMember.getId()).isEqualTo(lastId);
    }
}