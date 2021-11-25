package boardprogram.controller;

import boardprogram.DTO.CommentDTO;
import boardprogram.domain.Article;
import boardprogram.domain.Comment;
import boardprogram.service.ArticleService;
import boardprogram.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final ArticleService articleService;
    private final CommentService commentService;

    @PostMapping("/article/{articleId}")
    public String SaveComment(@ModelAttribute CommentDTO dto, @PathVariable Long articleId) {
        Article article = null;
        try {
            article = articleService.findArticleById(articleId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Comment comment = Comment.createRootComment(article, dto.getUsername(),
            dto.getContent());
        commentService.upload(comment);

        return "redirect:/article/" + articleId;
    }
}
