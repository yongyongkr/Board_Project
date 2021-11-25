package boardprogram.repository;

import boardprogram.domain.Article;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class JpaArticleRepository implements ArticleRepository {

    @PersistenceContext
    EntityManager em;

    @Override
    public Long save(Article article) {
        if (article.getId() != null) {
            em.merge(article);
        } else {
            em.persist(article);
        }
        return article.getId();
    }

    @Override
    public Optional<Article> findById(Long articleId) {
        return Optional.ofNullable(em.find(Article.class, articleId));
    }

    @Override
    public List<Article> findByName(String username) {
        return em.createQuery("select a from Article a where a.username = :name", Article.class)
            .setParameter("name", username)
            .getResultList();
    }

    @Override
    public List<Article> findAll() {
        return em.createQuery("select a from Article a", Article.class).getResultList();
    }

    @Override
    public List<Article> orderByView() {
        return em.createQuery("select a from Article a order by a.view desc", Article.class)
            .getResultList();
    }

    @Override
    public List<Article> orderByLikes() {
        return em.createQuery("select a from Article a order by a.likes desc", Article.class)
            .getResultList();
    }

    @Override
    public void delete(Long articleId) {
        em.createQuery("delete from Comment c where c.article.id = :id")
            .setParameter("id", articleId)
            .executeUpdate();
        em.createQuery("delete from Article a where a.id = :id")
            .setParameter("id", articleId)
            .executeUpdate();
        em.clear();
    }

    @Override
    public Integer plus(Long articleId) {
        em.createQuery("update Article a set a.likes = a.likes + 1 where a.id = :id")
            .setParameter("id", articleId)
            .executeUpdate();
        em.clear();

        Article findArticle = em.find(Article.class, articleId);
        return findArticle.getLikes();
    }

    @Override
    public Integer minus(Long articleId) {
        em.createQuery("update Article a set a.dislikes = a.dislikes + 1 where a.id = :id")
            .setParameter("id", articleId)
            .executeUpdate();
        em.clear();

        Article findArticle = em.find(Article.class, articleId);
        return findArticle.getDislikes();
    }
}

