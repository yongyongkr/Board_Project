package project.b.guest.book.repository;

import java.util.List;
import project.b.guest.book.domain.Article;

public interface ArticleRepository {

    Long save(Article article);

    List<Article> findById(Long id);

    List<Article> findByName(String name);

    List<Article> findAll();

    Long delete(Long id);

    void plus(Long id);

    void minus(Long id);
}
