package project.b.guest.book.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import project.b.guest.book.article.Article;
import project.b.guest.book.repository.ArticleRepository;
import project.b.guest.book.repository.MemoryArticleRepository;

class GuestBookServiceTest {

    GuestBookService guestBookService;
    ArticleRepository articleRepository;

    @BeforeEach
    public void beforeEach() {
        articleRepository = new MemoryArticleRepository();
        guestBookService = new GuestBookService(articleRepository);
    }

    @Test
    void upload() {
        //given
        Article article1 = new Article();
        article1.setName("김철수");
        article1.setContent("안녕하세요");

        //when
        Long saveId = guestBookService.upload(article1);

        //then
        Article findArticle = articleRepository.findById(saveId).get();
        assertEquals(article1.getContent(), findArticle.getContent());
    }

    @Test
    void deleteByDislikes() {
        //given
        Article article1 = new Article();
        article1.setName("김철수");
        article1.setContent("안녕하세요");
        guestBookService.upload(article1);

        for(int i=0; i<11; i++)
            articleRepository.minus(article1.getId());

        //when
        guestBookService.deleteByDislikes(article1);

        //then
        assertThat(articleRepository.findAll().size()).isEqualTo(0);
    }
}