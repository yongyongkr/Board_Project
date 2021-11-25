package boardprogram.service;

import boardprogram.domain.Article;
import boardprogram.repository.ArticleRepository;
import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public Long upload(Article article) {
        article.setCreateTime(LocalDateTime.now());
        article.setLastModifiedTime(LocalDateTime.now());
        articleRepository.save(article);
        return article.getId();
    }

    public void updateContent(Article article) {
        article.setLastModifiedTime(LocalDateTime.now());
        articleRepository.save(article);
    }

    public void delete(Long articleId) {
        articleRepository.delete(articleId);
    }

    public void increaseView(Article article) throws Exception {
        Article findArticle = articleRepository.findById(article.getId()).orElseThrow(
            () -> new Exception("cannot find"));
        findArticle.addView();
    }

    public List<Article> findAllArticles() {
        return articleRepository.findAll();
    }

    public Article findArticleById(Long articleId) throws Exception {
        return articleRepository.findById(articleId).orElseThrow(
            () -> new Exception("cannot find"));
    }

    public List<Article> findArticlesByUsername(String username) {
        return articleRepository.findByName(username);
    }

    public List<Article> popularArticlesByView() {
        return articleRepository.orderByView();
    }

    public List<Article> popularArticlesByLikes() {
        return articleRepository.orderByLikes();
    }

    public void likes(Long articleId) {
        articleRepository.plus(articleId);
    }

    public void dislikes(Long articleId) {
        Integer dislike = articleRepository.minus(articleId);
        if (dislike >= 10) {
            articleRepository.delete(articleId);
        }
    }
}
