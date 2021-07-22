package businesscardprogram.repository;

import businesscardprogram.domain.Member;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member("철수", "01012345678", "Naver");

        repository.save(member);
        Member result = repository.findByName("철").get();
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member("철수", "01012345678", "Naver");
        repository.save(member1);

        Member member2 = new Member("영희", "01023456789", "Kakao");
        repository.save(member2);

        Member result = repository.findByName("철").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member("철수", "01012345678", "Naver");
        repository.save(member1);

        Member member2 = new Member("영희", "01023456789", "Kakao");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
