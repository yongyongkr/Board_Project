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
    public Article save(Article article) {
        em.persist(article);
        return article;
    }

    @Override
    public Optional<Article> findById(Long id) {
        return Optional.ofNullable(
            em.createQuery("select a from Article a where a.id = :id", Article.class)
                .getSingleResult());
    }

    @Override
    public List<Article> findAll() {
        return em.createQuery("select a from Article a", Article.class).getResultList();
    }

    @Override
    public void delete(Long id) {
        em.createQuery("delete from Article a where a.id = :id");
    }

    @Override
    public void plus(Long id) {
        em.createQuery("update Article set likes = likes + 1L where id = :id");
    }

    @Override
    public void minus(Long id) {
        em.createQuery("update Article set dislikes = dislikes + 1L where id = :id");
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

//    @Override
//    public Optional<Time> validateTime(Article article) {
//        String name = article.getName();
//        Timestamp past = (Timestamp) em.createQuery(
//                "select max(a.time) from Article a where a.name = :name")
//            .setParameter("name", name)
//            .getSingleResult();
//        return (Optional<Time>) em.createQuery(
//            "select timestampdiff(minute, :past, current_timestamp) from Article a"
//                + "where a.name = :name")
//            .setParameter("past", past).setParameter("name", name).getSingleResult();
//    }
}
