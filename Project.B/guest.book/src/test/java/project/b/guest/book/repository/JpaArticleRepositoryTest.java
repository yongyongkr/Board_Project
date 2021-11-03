package project.b.guest.book.repository;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import project.b.guest.book.domain.Article;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
class JpaArticleRepositoryTest {

    @Autowired
    ArticleRepository jpaArticleRepository;

    @Test
    void save() {
        //given
        Article article = Article.createArticle("후라이 만드는법", "철수", "후라이팬에 기름을 두르고 굽는다");

        //when
        jpaArticleRepository.save(article);

        //then
        assertThat(jpaArticleRepository.findAll().size()).isEqualTo(1);
    }

    @Test
    void findById() {
        //given
        Article article = Article.createArticle("후라이 만드는법", "철수", "후라이팬에 기름을 두르고 굽는다");

        //when
        jpaArticleRepository.save(article);
        Article findArticle = jpaArticleRepository.findById(article.getId()).get();

        //then
        assertThat(findArticle.getId()).isEqualTo(article.getId());
        assertThat(findArticle.getContent()).isEqualTo(article.getContent());
    }

    @Test
    void findByName() {
        //given
        Article article1 = Article.createArticle("후라이 만드는법", "철수", "후라이팬에 기름을 두르고 굽는다");
        Article article2 = Article.createArticle("일기", "영희", "오늘 날씨 맑음");
        Article article3 = Article.createArticle("밥 먹을 사람?", "철수", "10분 뒤에 우리집으로");

        //when
        jpaArticleRepository.save(article1);
        jpaArticleRepository.save(article2);
        jpaArticleRepository.save(article3);

        //then
        assertThat(jpaArticleRepository.findByName("철수").size()).isEqualTo(2);
        assertThat(jpaArticleRepository.findByName("영희").size()).isEqualTo(1);
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
}
