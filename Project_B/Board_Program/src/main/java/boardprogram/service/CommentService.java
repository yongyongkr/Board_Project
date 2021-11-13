package boardprogram.service;

import boardprogram.domain.Article;
import boardprogram.domain.Comment;
import boardprogram.repository.CommentRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CommentService {

    private CommentRepository commentRepository;

    public void upload(Comment comment) {
        commentRepository.save(comment);
    }

    public List<Comment> commentList() {
        return commentRepository.findAll();
    }

    public void deleteByDislikes(Comment comment) {
        if (comment.getDislikes() >= 10) {
            commentRepository.delete(comment.getId());
        }
    }

    public void likes(Long id) {
        commentRepository.plus(id);
    }

    public void dislikes(Long id) {
        commentRepository.minus(id);
    }
}
