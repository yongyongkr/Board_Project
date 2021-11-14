package boardprogram.repository;

import boardprogram.domain.Comment;
import java.util.List;
import java.util.Optional;

public interface CommentRepository {

    Long save(Comment comment);

    Optional<Comment> findById(Long commentId);

    List<Comment> findByArticle(Long articleId);

    List<Comment> findAll();

    Long delete(Long commentId);

    Integer plus(Long commentId);

    Integer minus(Long commentId);
}
