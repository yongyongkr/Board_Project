package businesscardprogram.repository;

import static org.assertj.core.api.Assertions.assertThat;

import businesscardprogram.domain.Member;
import java.util.List;
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
    JpaMemberRepository jpaMemberRepository;

    @Test
    public void 저장() throws Exception {
        //given
        Member member = Member.createMember("Peter", "010-4218-5236", "Samsung");

        //when
        jpaMemberRepository.save(member);

        //then
        assertThat(jpaMemberRepository.findAll().size()).isEqualTo(1);
    }

    @Test
    public void 아이디로_조회() throws Exception {
        //given
        Member member1 = Member.createMember("Peter", "010-4218-5236", "Samsung");
        Member member2 = Member.createMember("Katie", "010-4543-2326", "Kakao");

        //when
        jpaMemberRepository.save(member1);
        jpaMemberRepository.save(member2);

        //then
        Long findMemberId = member1.getId();
        assertThat(jpaMemberRepository.findById(findMemberId).get().getName()).isEqualTo(
            member1.getName());
    }

    @Test
    public void 이름으로_조회() throws Exception {
        //given
        Member member1 = Member.createMember("Peter", "010-4218-5236", "Samsung");
        Member member2 = Member.createMember("Peter", "010-3228-5366", "Naver");
        Member member3 = Member.createMember("Katie", "010-4543-2326", "Kakao");

        //when
        jpaMemberRepository.save(member1);
        jpaMemberRepository.save(member2);
        jpaMemberRepository.save(member3);

        //then
        assertThat(jpaMemberRepository.findByName("Peter").size()).isEqualTo(2);
    }

    @Test
    public void 전체조회() throws Exception {
        //given
        Member member1 = Member.createMember("Peter", "010-4218-5236", "Samsung");
        Member member2 = Member.createMember("Peter", "010-3228-5366", "Naver");
        Member member3 = Member.createMember("Katie", "010-4543-2326", "Kakao");

        //when
        jpaMemberRepository.save(member1);
        jpaMemberRepository.save(member2);
        jpaMemberRepository.save(member3);

        //then
        assertThat(jpaMemberRepository.findAll().size()).isEqualTo(3);
    }

    @Test
    public void 마지막_아이디_조회() throws Exception {
        //given
        Member member1 = Member.createMember("Peter", "010-4218-5236", "Samsung");
        Member member2 = Member.createMember("Peter", "010-3228-5366", "Naver");
        Member member3 = Member.createMember("Katie", "010-4543-2326", "Kakao");

        //when
        jpaMemberRepository.save(member1);
        jpaMemberRepository.save(member2);
        Long lastId = jpaMemberRepository.save(member3);

        //then
        Member lastMember = jpaMemberRepository.findTop1ByOrderByIdDesc().get(0);
        assertThat(lastMember.getId()).isEqualTo(lastId);
    }
}