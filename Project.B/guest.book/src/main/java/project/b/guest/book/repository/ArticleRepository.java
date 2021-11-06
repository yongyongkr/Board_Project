package project.b.guest.book.repository;

import java.util.List;
import java.util.Optional;
import project.b.guest.book.domain.Article;

public interface ArticleRepository {

    Long save(Article article);

    Optional<Article> findById(Long id);

    List<Article> findByName(String name);

    List<Article> findAll();

    void delete(Long id);
}
