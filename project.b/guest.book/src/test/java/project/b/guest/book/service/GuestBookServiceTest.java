package project.b.guest.book.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import project.b.guest.book.article.Article;
import project.b.guest.book.repository.ArticleRepository;
import project.b.guest.book.repository.MemoryArticleRepository;

class GuestBookServiceTest {

    GuestBookService guestBookService;
    MemoryArticleRepository articleRepository;

    @BeforeEach
    public void beforeEach() {
        articleRepository = new MemoryArticleRepository();
        guestBookService = new GuestBookService(articleRepository);
    }

    @AfterEach
    public void afterEach() {
        articleRepository.clearStore();
    }

    @Test
    @DisplayName("게시물 업로드")
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
    @DisplayName("싫어요가 10개 이상이면 삭제")
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