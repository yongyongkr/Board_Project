package boardprogram.service;

import boardprogram.domain.Comment;
import boardprogram.repository.CommentRepository;
import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public void upload(Comment comment) {
        commentRepository.save(comment);
    }

    public List<Comment> findAllComments() {
        return commentRepository.findAll();
    }

    public List<Comment> findCommentsByArticleId(Long articleId) {
        return commentRepository.findByArticle(articleId);
    }

    public void likes(Long commentId) {
        commentRepository.plus(commentId);
    }

    public void dislikes(Long commentId) {
        Integer dislike = commentRepository.minus(commentId);
        if (dislike >= 0) {
            commentRepository.delete(commentId);
        }
    }
}
