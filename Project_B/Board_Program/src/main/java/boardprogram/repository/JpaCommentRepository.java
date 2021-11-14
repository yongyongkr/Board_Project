package boardprogram.repository;

import boardprogram.domain.Comment;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
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
        return em.createQuery(
                "select c from Comment c where c.article.id = :id order by c.createTime desc",
                Comment.class)
            .setParameter("id", articleId)
            .getResultList();
    }

    @Override
    public List<Comment> findAll() {
        return em.createQuery("select c from Comment c", Comment.class).getResultList();
    }

    @Override
    public Long delete(Long commentId) {
        em.createQuery("update Comment c set c.content = :content where c.id = :id")
            .setParameter("content", "삭제된 댓글입니다")
            .setParameter("id", commentId)
            .executeUpdate();
        em.clear();

        return commentId;
    }

    @Override
    public Integer plus(Long commentId) {
        em.createQuery("update Comment c set c.likes = c.likes + 1 where c.id = :id")
            .setParameter("id", commentId)
            .executeUpdate();
        em.clear();

        Comment findComment = em.find(Comment.class, commentId);
        return findComment.getLikes();
    }

    @Override
    public Integer minus(Long commentId) {
        em.createQuery("update Comment c set c.dislikes = c.dislikes + 1 where c.id = :id")
            .setParameter("id", commentId)
            .executeUpdate();
        em.clear();

        Comment findComment = em.find(Comment.class, commentId);
        return findComment.getDislikes();
    }
}
