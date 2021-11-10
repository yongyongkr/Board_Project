package boardprogram.repository;

import boardprogram.domain.Article;
import java.util.List;
import java.util.Optional;

public interface ArticleRepository {

    Long save(Article article);

    Optional<Article> findById(Long id);

    List<Article> findByName(String name);

    List<Article> findAll();

    void delete(Long id);

    void plus(Long id);

    void minus(Long id);
}
