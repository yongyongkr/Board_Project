package project.b.guest.book.repository;

import java.awt.Robot;
import java.util.List;
import java.util.Optional;
import project.b.guest.book.article.Article;

public interface ArticleRepository {

    void save(Article article);

    Optional<Article> findById(Long id);

    Optional<Long> findByName(String name);

    List<Article> findAll();

    void delete(Long id);

    void plus(Long id);

    void minus(Long id);

    boolean existOrNot(Article article);

    int validateTime(Article article);

    static void DelayTime(int delaySec) throws Exception {
        Robot robot = new Robot();
        robot.delay(delaySec * 1000);
    }
}
