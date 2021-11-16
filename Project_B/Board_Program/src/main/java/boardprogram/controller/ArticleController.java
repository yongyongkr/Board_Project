package boardprogram.controller;

import boardprogram.DTO.ArticleDTO;
import boardprogram.domain.Article;
import boardprogram.service.ArticleService;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

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
        model.addAttribute("article", article);
        return "article/articlePage";
    }

    @GetMapping("/{username}/information")
    public String SearchByUsername(@PathVariable String username, Model model) {
        List<Article> articles = articleService.findArticlesByUsername(username);
        model.addAttribute("articles", articles);
        return "/article/SearchUsername";
    }

    @GetMapping("/article/post")
    public String ArticleForm() {
        return "/article/articleForm";
    }

    @PostMapping("/article/post")
    public String Post(@ModelAttribute ArticleDTO articleDTO) {
        Article article = Article.createArticle(articleDTO.getTitle(), articleDTO.getUsername(),
            articleDTO.getContent());
        articleService.upload(article);
        return "redirect:/";
    }

    @PostConstruct
    public void init() {
        Article article1 = Article.createArticle("후라이 만드는법", "철수", "후라이팬에 기름을 두르고 굽는다");
        Article article2 = Article.createArticle("일기", "영희", "오늘 날씨 맑음");
        Article article3 = Article.createArticle("밥 먹을 사람?", "철수", "10분 뒤에 우리집으로");

        articleService.upload(article1);
        articleService.upload(article2);
        articleService.upload(article3);
    }
}
