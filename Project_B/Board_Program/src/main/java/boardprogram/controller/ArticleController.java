package boardprogram.controller;

import boardprogram.DTO.ArticleCreateForm;
import boardprogram.DTO.ArticleUpdateForm;
import boardprogram.domain.Article;
import boardprogram.domain.Comment;
import boardprogram.service.ArticleService;
import boardprogram.service.CommentService;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;
    private final CommentService commentService;

    @GetMapping
    public String Main(Model model) {
        List<Article> articles = articleService.findAllArticles();
        model.addAttribute("articles", articles);
        return "main";
    }

    @GetMapping("/article/{articleId}")
    public String Article(@PathVariable Long articleId, Model model) {
        Article article = null;
        try {
            article = articleService.findArticleById(articleId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<Comment> comments = commentService.findCommentsByArticleId(articleId);

        try {
            articleService.increaseView(article);
        } catch (Exception e) {
            e.printStackTrace();
        }

        model.addAttribute("article", article);
        model.addAttribute("comments", comments);

        return "article/articlePage";
    }

    @GetMapping("/{username}/information")
    public String SearchByUsername(@PathVariable String username, Model model) {
        List<Article> articles = articleService.findArticlesByUsername(username);
        model.addAttribute("articles", articles);
        return "/article/SearchUsername";
    }

    @GetMapping("/article/post")
    public String CreateForm() {
        return "/article/articleCreateForm";
    }

    @PostMapping("/article/post")
    public String Post(@ModelAttribute ArticleCreateForm form) {
        Article article = Article.createArticle(form.getTitle(), form.getUsername(),
            form.getContent());
        articleService.upload(article);
        return "redirect:/article/" + article.getId();
    }

    @GetMapping("/article/update/{articleId}")
    public String UpdateForm(@PathVariable String articleId) {
        return "/article/articleUpdateForm";
    }

    @PostMapping("/article/update/{articleId}")
    public String UpdateArticle(@PathVariable Long articleId,
        @ModelAttribute ArticleUpdateForm form) {
        Article article = null;
        try {
            article = articleService.findArticleById(articleId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Article updatedArticle = Article.updateArticle(article, form.getTitle(), form.getContent());

        articleService.updateContent(updatedArticle);
        return "redirect:/article/" + articleId;
    }

    @DeleteMapping("/article/delete/{articleId}")
    public String DeleteArticle(@PathVariable Long articleId) {
        articleService.delete(articleId);
        return "redirect:/";
    }

    @PostConstruct
    public void init() {
        Article article = Article.createArticle("아이디 추출용", "지석", "id 값이 필요합니다");
        articleService.upload(article);

        Comment rootComment1 = Comment.createRootComment(article, "철수", "좋은 글 감사합니다");
        commentService.upload(rootComment1);
        Comment leafComment1 = Comment.createLeafComment(article, rootComment1, "영희", "동감합니다");
        Comment leafComment2 = Comment.createLeafComment(article, rootComment1, "세찬", "인정합니다");
        Comment leafComment3 = Comment.createLeafComment(article, rootComment1, "영희", "저는 반대합니다");
        commentService.upload(leafComment1);
        commentService.upload(leafComment2);
        commentService.upload(leafComment3);

        Comment rootComment2 = Comment.createRootComment(article, "미애", "이건 아니죠");
        commentService.upload(rootComment2);
        Comment leafComment4 = Comment.createLeafComment(article, rootComment2, "철수", "왜요?");
        Comment leafComment5 = Comment.createLeafComment(article, rootComment2, "철수",
            "저는 맞는것 같은데요?");
        Comment leafComment6 = Comment.createLeafComment(article, rootComment2, "미애", "그냥요");
        commentService.upload(leafComment4);
        commentService.upload(leafComment5);
        commentService.upload(leafComment6);

        Article article2 = Article.createArticle("아이디 추출용", "지석", "id 값이 필요합니다");
        articleService.upload(article2);

        Comment rootComment3 = Comment.createRootComment(article2, "철수", "좋은 글 감사합니다");
        commentService.upload(rootComment3);
        Comment leafComment7 = Comment.createLeafComment(article2, rootComment3, "영희", "동감합니다");
        commentService.upload(leafComment7);

        Article article3 = Article.createArticle("후라이 만드는법", "철수", "후라이팬에 기름을 두르고 굽는다");
        Article article4 = Article.createArticle("일기", "영희", "오늘 날씨 맑음");
        Article article5 = Article.createArticle("밥 먹을 사람?", "철수", "10분 뒤에 우리집으로");

        articleService.upload(article3);
        articleService.upload(article4);
        articleService.upload(article5);
    }
}
