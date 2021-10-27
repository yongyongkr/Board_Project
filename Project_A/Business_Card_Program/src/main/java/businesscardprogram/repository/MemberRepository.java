package businesscardprogram.repository;

import businesscardprogram.domain.Member;
import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Long save(Member member);

    Optional<Member> findById(Long id);

    List<Member> findByName(String name);

    List<Member> findAll();
}
