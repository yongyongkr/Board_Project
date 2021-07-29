package project.b.guest.book.repository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import project.b.guest.book.article.Article;

class MemoryArticleRepositoryTest {

    MemoryArticleRepository repository = new MemoryArticleRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    void save() {
        //given
        Article article1 = new Article();
        article1.setName("김철수");
        article1.setContent("안녕하세요");

        Article article2 = new Article();
        article2.setName("김영희");
        article2.setContent("반갑습니다");

        //when
        repository.save(article1);
        repository.save(article2);

        //then
        Article result = repository.findById(article1.getId()).get();
        assertThat(result).isEqualTo(article1);
    }

    @Test
    void findAll() {
        //given
        Article article1 = new Article();
        article1.setName("김철수");
        article1.setContent("안녕하세요");
        repository.save(article1);

        Article article2 = new Article();
        article2.setName("김영희");
        article2.setContent("반갑습니다");
        repository.save(article2);

        //when
        List<Article> resultList = repository.findAll();

        //then
        assertThat(resultList.size()).isEqualTo(2);
    }

    @Test
    void delete() {
        //given
        Article article1 = new Article();
        article1.setName("김철수");
        article1.setContent("안녕하세요");
        repository.save(article1);

        Article article2 = new Article();
        article2.setName("김영희");
        article2.setContent("반갑습니다");
        repository.save(article2);

        //when
        repository.delete(article1.getId());
        List<Article> articleList = repository.findAll();

        //then
        assertThat(articleList.size()).isEqualTo(1);
    }

    @Test
    void plus() {
        //given
        Article article1 = new Article();
        article1.setName("김철수");
        article1.setContent("안녕하세요");
        repository.save(article1);

        //when
        repository.plus(article1.getId());
        repository.plus(article1.getId());
        Long result = article1.getLikes();

        //then
        assertThat(result).isEqualTo(2L);
    }

    @Test
    void minus() {
        //given
        Article article1 = new Article();
        article1.setName("김철수");
        article1.setContent("안녕하세요");
        repository.save(article1);

        //when
        repository.minus(article1.getId());
        repository.minus(article1.getId());
        Long result = article1.getDislikes();

        //then
        assertThat(result).isEqualTo(2L);
    }

    @Test
    void existOrNot() {
        //given
        Article article1 = new Article();
        article1.setName("김철수");
        article1.setContent("안녕하세요");
        repository.save(article1);

        //when

        //then
        assertTrue(repository.existOrNot(article1));
    }
}