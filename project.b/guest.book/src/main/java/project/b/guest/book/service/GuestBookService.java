package project.b.guest.book.service;

import java.util.List;
import javax.transaction.Transactional;
import project.b.guest.book.article.Article;
import project.b.guest.book.repository.ArticleRepository;
import project.b.guest.book.repository.JpaArticleRepository;

@Transactional
public class GuestBookService {

    private final ArticleRepository articleRepository;

    public GuestBookService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Long upload(Article article) {
        if (articleRepository.existOrNot(article)) {
            return -1L;
        } else {
            articleRepository.save(article);
            return article.getId();
        }
    }

    List<Article> articleList() {
        return articleRepository.findAll();
    }

    void deleteByDislikes(Article article) {
        if (article.getDislikes() >= 10)
            articleRepository.delete(article.getId());
    }

    void likes(Long id) {
        articleRepository.plus(id);
    }

    void dislikes(Long id) {
        articleRepository.minus(id);
    }
}
