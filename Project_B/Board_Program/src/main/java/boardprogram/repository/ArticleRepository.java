package boardprogram.repository;

import boardprogram.domain.Article;
import java.util.List;
import java.util.Optional;

public interface ArticleRepository {

    Long save(Article article);

    Optional<Article> findById(Long articleId);

    List<Article> findByName(String name);

    List<Article> findAll();

    void delete(Long articleId);

    Integer plus(Long articleId);

    Integer minus(Long articleId);
}
