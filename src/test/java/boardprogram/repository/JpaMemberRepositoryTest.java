package boardprogram.repository;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import boardprogram.domain.Gender;
import boardprogram.domain.Member;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaMemberRepositoryTest {

	@Autowired
	MemberRepository memberRepository;

	@Test
	public void 저장_및_조회() throws Exception {
		//given
		Member member = Member.createMember("승우", Gender.MALE, "010-2425-1242", "dfsd@gmail.com",
			LocalDate.of(1993, 3, 2));

		//when
		Long saveId = memberRepository.save(member);
		Member findMember = memberRepository.findById(saveId)
			.orElseThrow(() -> new Exception("cannot find"));

		//then
		assertThat(memberRepository.findAll().size()).isEqualTo(1);
		assertThat(member.getBirthday()).isEqualTo(findMember.getBirthday());
		assertThat(member.getEmail()).isEqualTo(findMember.getEmail());
		assertThat(member.getPhoneNumber()).isEqualTo(findMember.getPhoneNumber());
		assertThat(member.getUsername()).isEqualTo(findMember.getUsername());
	}
}
