package project.b.guest.book.repository;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import project.b.guest.book.domain.Article;

@Repository
public class JpaArticleRepository implements ArticleRepository {

    @PersistenceContext
    EntityManager em;

    @Override
    public Long save(Article article) {
        em.persist(article);
        return article.getId();
    }

    @Override
    public Optional<Article> findById(Long id) {
        return Optional.ofNullable(em.find(Article.class, id));
    }

    @Override
    public Optional<Article> findByName(String name) {
        return em.createQuery("select a from Article a where ");
    }

    @Override
    public List<Article> findAll() {
        return em.createQuery("select a from Article a").getResultList();
    }

    @Override
    public void delete(Long id) {
        em.createQuery("delete from Article a where a.id = :id");
    }

    @Override
    public void plus(Long id) {
        em.createQuery("update Article set likes = likes + 1 where id = :id");
    }

    @Override
    public void minus(Long id) {
        em.createQuery("update Article set dislikes = dislikes + 1 where id = :id");
    }

    @Override
    public boolean existOrNot(Article article) {
        try {
            em.createQuery("select a from Article a where a.name = :name")
                .setParameter("name", article.getName());
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public int validateTime(Article article) {
        if (existOrNot(article)) {
            Long lastTime = findByName(article.getName()).get();
            return em.createQuery(
                "select timestampdiff(second, :lastTime, article.getTime)").getFirstResult();
        } else {
            return 0;
        }
    }
}

