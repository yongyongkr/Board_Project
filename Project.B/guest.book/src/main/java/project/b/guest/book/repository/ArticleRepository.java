package project.b.guest.book.repository;

import java.awt.Robot;
import java.util.List;
import java.util.Optional;
import project.b.guest.book.domain.Article;

public interface ArticleRepository {

    Long save(Article article);

    Optional<Article> findById(Long id);

    Optional<Article> findByName(String name);

    List<Article> findAll();

    Long delete(Long id);

    void plus(Long id);

    void minus(Long id);
}
