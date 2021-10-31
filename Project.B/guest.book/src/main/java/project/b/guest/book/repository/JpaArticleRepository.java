/*
package project.b.guest.book.repository;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import project.b.guest.book.article.Article;

public class JpaArticleRepository implements ArticleRepository {

    private final EntityManager em;

    public JpaArticleRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public void save(Article article) {
        em.persist(article);
    }

    @Override
    public Optional<Article> findById(Long id) {
        return Optional.ofNullable(
            em.createQuery("select a from Article a where a.id = :id", Article.class)
                .getSingleResult());
    }

    @Override
    public Optional<Long> findByName(String name) {
        return em.createQuery("select max(a.time) from Article a where a.name = :name")
            .getResultStream().findFirst();
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

*/
