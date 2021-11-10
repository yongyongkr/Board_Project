package boardprogram.repository;

import boardprogram.domain.Comment;
import java.util.List;
import java.util.Optional;

public interface CommentRepository {

    Long save(Comment comment);

    Optional<Comment> findById(Long id);

    List<Comment> findByArticle(Long articleId);

    List<Comment> findAll();

    void delete(Long id);

    void plus(Long id);

    void minus(Long id);
}
