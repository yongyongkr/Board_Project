package boardprogram.repository;

import boardprogram.domain.Comment;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class JpaCommentRepository implements CommentRepository {

    @PersistenceContext
    EntityManager em;

    @Override
    public Long save(Comment comment) {
        em.persist(comment);
        return comment.getId();
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return Optional.ofNullable(em.find(Comment.class, id));
    }

    @Override
    public List<Comment> findByArticle(Long articleId) {
        return em.createQuery("select c from Comment c where c.article.id = :id order by c.createTime desc")
            .setParameter("id", articleId)
            .getResultList();
    }

    @Override
    public List<Comment> findAll() {
        return em.createQuery("select c from Comment c", Comment.class).getResultList();
    }

    @Override
    public void delete(Long commentId) {
        em.createQuery("delete from Comment c where c.id = :id")
            .setParameter("id", commentId)
            .executeUpdate();
    }
}
