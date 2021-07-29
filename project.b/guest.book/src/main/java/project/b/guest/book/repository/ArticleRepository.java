package project.b.guest.book.repository;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Optional;
import project.b.guest.book.article.Article;

public interface ArticleRepository {

    Article save(Article article);
    Optional<Article> findById(Long id);
    List<Article> findAll();
    void delete(Long id);
    void plus(Long id);
    void minus(Long id);
    boolean existOrNot(Article article);
//    Optional<Time> validateTime(Article article);
}
