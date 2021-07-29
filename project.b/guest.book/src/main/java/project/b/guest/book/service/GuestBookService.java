package project.b.guest.book.service;

import java.util.List;
import project.b.guest.book.article.Article;
import project.b.guest.book.repository.ArticleRepository;

public class GuestBookService {

    private final ArticleRepository articleRepository;

    public GuestBookService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Long upload(Article article) {
        articleRepository.save(article);
        return article.getId();
    }

    public List<Article> articleList() {
        return articleRepository.findAll();
    }

    public void deleteByDislikes(Article article) {
        if (article.getDislikes() >= 10) {
            articleRepository.delete(article.getId());
        }
    }

    public void likes(Long id) {
        articleRepository.plus(id);
    }

    public void dislikes(Long id) {
        articleRepository.minus(id);
    }
}
