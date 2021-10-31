/*
package project.b.guest.book.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import project.b.guest.book.article.Article;

@Transactional
@SpringBootTest
class JpaArticleRepositoryTest {

    private final EntityManager em;
    JpaArticleRepositoryTest(EntityManager em) {
        this.em = em;
    }

    JpaArticleRepository jpaArticleRepository;


    @Test
    void save() {
        //given
        Article article = new Article();
        article.setName("철수");
        article.setContext("hello");

        //when
        jpaArticleRepository.save(article);

    }

    @Test
    void findById() {
    }

    @Test
    void findByName() {
    }

    @Test
    void findAll() {
        List<Article> articleList = jpaArticleRepository.findAll();
        System.out.println("articleList = " + articleList);
    }

    @Test
    void delete() {
    }

    @Test
    void plus() {
    }

    @Test
    void minus() {
    }

    @Test
    void existOrNot() {
    }

    @Test
    void validateTime() {
    }
}*/
