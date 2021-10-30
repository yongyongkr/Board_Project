package project.b.guest.book.service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import project.b.guest.book.article.Article;
import project.b.guest.book.repository.JpaArticleRepository;

@Transactional
class GuestBookServiceTest {

    private final EntityManager em;
    GuestBookServiceTest(EntityManager em) {
        this.em = em;
    }

    GuestBookService guestBookService = new GuestBookService(new JpaArticleRepository(em));

    @Test
    void upload() {
        //given
        Article article = new Article();
        article.setName("영수");
        article.setContext("hello");
        System.out.println("article = " + article);

        //when
        guestBookService.upload(article);

        //then
        List<Article> articles = guestBookService.articleList();
        System.out.println("articles = " + articles);
    }

    @Test
    void articleList() {
        //given

        //when
        List<Article> articles = guestBookService.articleList();
        Assertions.assertThat(articles.size()).isEqualTo(0);
    }

    @Test
    void deleteByDislikes() {
    }

    @Test
    void likes() {
    }

    @Test
    void dislikes() {
    }
}