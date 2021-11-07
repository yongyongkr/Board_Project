package project.b.guest.book.repository;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
    public Optional<Article> findById(Long articleId) {
        return Optional.ofNullable(em.find(Article.class, articleId));
    }

    @Override
    public List<Article> findByName(String username) {
        return em.createQuery("select a from Article a where a.username = :name")
            .setParameter("name", username)
            .getResultList();
    }

    @Override
    public List<Article> findAll() {
        return em.createQuery("select a from Article a").getResultList();
    }

    @Override
    public void delete(Long articleId) {
        em.createQuery("delete from Article a where a.id = :id")
            .setParameter("id", articleId)
            .executeUpdate();
    }
}

