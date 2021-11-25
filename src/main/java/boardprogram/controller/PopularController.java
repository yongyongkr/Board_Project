package boardprogram.controller;

import boardprogram.domain.Article;
import boardprogram.domain.Comment;
import boardprogram.service.ArticleService;
import boardprogram.service.CommentService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class PopularController {

    private final ArticleService articleService;
    private final CommentService commentService;

    @GetMapping("/popular")
    public String Articles(Model model) {
        List<Article> articlesByView = articleService.popularArticlesByView();
        List<Article> articlesByLikes = articleService.popularArticlesByLikes();
        List<Comment> commentsByLikes = commentService.popularCommentsByLikes();

        model.addAttribute("articlesByView", articlesByView);
        model.addAttribute("articlesByLikes", articlesByLikes);
        model.addAttribute("commentsByLikes", commentsByLikes);

        return "/popular/articlesAndComments";
    }
}
