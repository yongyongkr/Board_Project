package boardprogram.repository;

import java.util.List;
import java.util.Optional;

import boardprogram.domain.Member;

public interface MemberRepository {

	Long save(Member member);

	Optional<Member> findById(Long memberId);

	Optional<Member> findByName(String name);

	List<Member> findAll();
}
