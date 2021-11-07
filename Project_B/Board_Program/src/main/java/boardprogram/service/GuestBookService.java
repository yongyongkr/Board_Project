/*
package boardprogram.service;

import java.util.List;
import project.b.guest.book.article.Article;
import boardprogram.repository.ArticleRepository;

public class GuestBookService {

    private ArticleRepository articleRepository;

    public GuestBookService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public void upload(Article article) {
        articleRepository.save(article);
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
}*/
