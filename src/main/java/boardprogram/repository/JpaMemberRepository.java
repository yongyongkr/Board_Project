package boardprogram.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import boardprogram.domain.Member;

@Repository
public class JpaMemberRepository implements MemberRepository {

	@PersistenceContext
	EntityManager em;

	@Override
	public Long save(Member member) {
		if (member.getId() != null) {
			em.merge(member);
		} else {
			em.persist(member);
		}
		return member.getId();
	}

	@Override
	public Optional<Member> findById(Long memberId) {
		return Optional.ofNullable(em.find(Member.class, memberId));
	}

	@Override
	public Optional<Member> findByName(String username) {
		return em.createQuery("select m from Member m where m.username = :name", Member.class)
			.setParameter("name", username)
			.setMaxResults(1)
			.getResultList()
			.stream()
			.findAny();
	}

	@Override
	public List<Member> findAll() {
		return em.createQuery("select m from Member m", Member.class).getResultList();
	}
}
