package boardprogram.controller;

import boardprogram.DTO.ArticleCreateForm;
import boardprogram.DTO.ArticleUpdateForm;
import boardprogram.domain.Article;
import boardprogram.domain.Comment;
import boardprogram.domain.Gender;
import boardprogram.domain.Member;
import boardprogram.service.ArticleService;
import boardprogram.service.CommentService;

import java.time.LocalDate;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;
    private final CommentService commentService;

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
    public String FindByUsername(@PathVariable String username, Model model) {
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
        Member member = Member.createMember("??????", Gender.MALE, "010-1234-1234", "jdw@gmail.com", LocalDate.of(1990, 3, 21));

        Article article = Article.createArticle(member, "????????? ?????????", "??????", "id ?????? ???????????????");
        articleService.upload(article);

        Comment rootComment1 = Comment.createRootComment(article, "??????", "?????? ??? ???????????????");
        commentService.upload(rootComment1);
        Comment leafComment1 = Comment.createLeafComment(article, rootComment1, "??????", "???????????????");
        Comment leafComment2 = Comment.createLeafComment(article, rootComment1, "??????", "???????????????");
        Comment leafComment3 = Comment.createLeafComment(article, rootComment1, "??????", "?????? ???????????????");
        commentService.upload(leafComment1);
        commentService.upload(leafComment2);
        commentService.upload(leafComment3);

        Comment rootComment2 = Comment.createRootComment(article, "??????", "?????? ?????????");
        commentService.upload(rootComment2);
        Comment leafComment4 = Comment.createLeafComment(article, rootComment2, "??????", "???????");
        Comment leafComment5 = Comment.createLeafComment(article, rootComment2, "??????",
            "?????? ????????? ?????????????");
        Comment leafComment6 = Comment.createLeafComment(article, rootComment2, "??????", "?????????");
        commentService.upload(leafComment4);
        commentService.upload(leafComment5);
        commentService.upload(leafComment6);

        Article article2 = Article.createArticle(member, "????????? ?????????", "??????", "id ?????? ???????????????");
        articleService.upload(article2);

        Comment rootComment3 = Comment.createRootComment(article2, "??????", "?????? ??? ???????????????");
        commentService.upload(rootComment3);
        Comment leafComment7 = Comment.createLeafComment(article2, rootComment3, "??????", "???????????????");
        commentService.upload(leafComment7);

        Article article3 = Article.createArticle(member, "????????? ????????????", "??????", "??????????????? ????????? ????????? ?????????");
        Article article4 = Article.createArticle(member, "??????", "??????", "?????? ?????? ??????");
        Article article5 = Article.createArticle(member, "??? ?????? ???????", "??????", "10??? ?????? ???????????????");

        articleService.upload(article3);
        articleService.upload(article4);
        articleService.upload(article5);
    }
}
