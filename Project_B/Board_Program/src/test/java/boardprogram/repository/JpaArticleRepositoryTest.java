package boardprogram.repository;

import static org.assertj.core.api.Assertions.assertThat;

import boardprogram.domain.Article;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
class JpaArticleRepositoryTest {

    @Autowired
    ArticleRepository articleRepository;

    @Test
    void 저장_및_전체조회() {
        //given
        saveThreeArticle();

        //then
        assertThat(articleRepository.findAll().size()).isEqualTo(3);
    }

    @Test
    void 아이디로_조회() {
        //given
        Long saveId = saveOneArticleAndGetId();

        //when
        Article findArticle = articleRepository.findById(saveId).get();

        //then
        assertThat(findArticle.getTitle()).isEqualTo("아이디 추출용");
        assertThat(findArticle.getUsername()).isEqualTo("지석");
    }

    @Test
    void 이름으로_조회() {
        //given
        saveThreeArticle();

        //then
        assertThat(articleRepository.findByName("철수").size()).isEqualTo(2);
        assertThat(articleRepository.findByName("영희").size()).isEqualTo(1);
    }

    @Test
    void 삭제() {
        //given
        Long saveId = saveOneArticleAndGetId();
        assertThat(articleRepository.findAll().size()).isEqualTo(1);

        //when
        articleRepository.delete(saveId);

        //then
        assertThat(articleRepository.findAll().size()).isEqualTo(0);
    }

    private void saveThreeArticle() {
        Article article1 = Article.createArticle("후라이 만드는법", "철수", "후라이팬에 기름을 두르고 굽는다");
        Article article2 = Article.createArticle("일기", "영희", "오늘 날씨 맑음");
        Article article3 = Article.createArticle("밥 먹을 사람?", "철수", "10분 뒤에 우리집으로");

        articleRepository.save(article1);
        articleRepository.save(article2);
        articleRepository.save(article3);
    }

    private Long saveOneArticleAndGetId() {
        Article article = Article.createArticle("아이디 추출용", "지석", "id 값이 필요합니다");
        return articleRepository.save(article);
    }
}
