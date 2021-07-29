package project.b.guest.book.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static project.b.guest.book.repository.ArticleRepository.DelayTime;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import project.b.guest.book.article.Article;

class MemoryArticleRepositoryTest {

    MemoryArticleRepository repository = new MemoryArticleRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    @DisplayName("메모리에 저장")
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
    @DisplayName("Id를 통해 Article 찾기")
    void findById() {
        //given
        Article article1 = new Article();
        article1.setName("김철수");
        article1.setContent("안녕하세요");
        repository.save(article1);

        //when
        Article result = repository.findById(article1.getId()).get();

        //then
        assertThat(result).isEqualTo(article1);
    }

    @Test
    @DisplayName("같은 이름으로 등록한 게시물 중 가장 최근 게시물의 작성 시간 반환")
    void findByName() throws Exception {
        //given
        Article article1 = new Article();
        article1.setName("김철수");
        article1.setContent("안녕하세요");
        repository.save(article1);

        DelayTime(5);

        Article article2 = new Article();
        article2.setName("김철수");
        article2.setContent("만나서 반갑습니다");
        repository.save(article2);

        //when
        long uploadTime = repository.findByName("김철수");

        //then
        assertThat(uploadTime).isEqualTo(article2.getTime());
    }

    @Test
    @DisplayName("모든 목록 조회")
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
    @DisplayName("특정 id 게시글 삭제")
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
    @DisplayName("특정 id 게시물 좋아요 + 1")
    void plus() {
        //given
        Article article1 = new Article();
        article1.setName("김철수");
        article1.setContent("안녕하세요");
        repository.save(article1);

        //when
        repository.plus(article1.getId());
        repository.plus(article1.getId());
        int result = article1.getLikes();

        //then
        assertThat(result).isEqualTo(2);
    }

    @Test
    @DisplayName("특정 id 게시물 싫어요 + 1")
    void minus() {
        //given
        Article article1 = new Article();
        article1.setName("김철수");
        article1.setContent("안녕하세요");
        repository.save(article1);

        //when
        repository.minus(article1.getId());
        repository.minus(article1.getId());
        int result = article1.getDislikes();

        //then
        assertThat(result).isEqualTo(2);
    }

    @Test
    @DisplayName("게시물을 올린 사람이 이전에도 올렸는가")
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

    @Test
    @DisplayName("특정 이름이 이전에 게시물 올린적 있으면 최근 게시물과의 시간 차이 반환, 없으면 0 반환")
    void validateTime() throws Exception {
        //given
        Article article1 = new Article();
        article1.setName("김철수");
        article1.setContent("안녕하세요");
        repository.save(article1);
        long article1Time = repository.validateTime(article1);

        Article article2 = new Article();
        article2.setName("김영희");
        article2.setContent("반갑습니다");
        repository.save(article2);
        long article2Time = repository.validateTime(article2);

        //when
        DelayTime(10);

        Article article3 = new Article();
        article3.setName("김철수");
        article3.setContent("어디 사세요?");
        repository.save(article3);
        long article3Time = repository.validateTime(article3);

        //then
        System.out.println("article2Time - article1Time = " + (article2Time - article1Time));
        System.out.println("article3Time - article1Time = " + (article3Time - article2Time));
    }
}