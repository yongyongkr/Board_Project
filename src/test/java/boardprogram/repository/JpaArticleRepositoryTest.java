package boardprogram.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import boardprogram.domain.Article;
import boardprogram.domain.Comment;
import boardprogram.domain.Gender;
import boardprogram.domain.Member;

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
    @Autowired
    CommentRepository commentRepository;

    @Test
    void 저장_및_전체조회() {
        //given
        saveThreeArticle();

        //then
        assertThat(articleRepository.findAll().size()).isEqualTo(3);
    }

    @Test
    void 아이디로_조회() throws Exception {
        //given
        Long saveId = saveOneArticleAndGetId();

        //when
        Article findArticle = articleRepository.findById(saveId)
            .orElseThrow(() -> new Exception("cannot find"));

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

    @Test
    public void 댓글까지_삭제() throws Exception {
        //given
        Long articleId = saveOneCommentAndGetArticleId();

        //when
        articleRepository.delete(articleId);

        //then
        assertThat(articleRepository.findAll().size()).isEqualTo(0);
        assertThat(commentRepository.findAll().size()).isEqualTo(0);
    }

    @Test
    public void 좋아요() throws Exception {
        //given
        Long savedId = saveOneArticleAndGetId();
        Article findArticle = articleRepository.findById(savedId)
            .orElseThrow(() -> new Exception("cannot find"));
        assertThat(findArticle.getLikes()).isEqualTo(0);

        //when
        Integer updatedLike = articleRepository.plus(savedId);
        Article updateArticle = articleRepository.findById(savedId)
            .orElseThrow(() -> new Exception("cannot find"));

        //then
        assertThat(updateArticle.getLikes()).isEqualTo(1);
        assertThat(updatedLike).isEqualTo(1);
    }

    @Test
    public void 싫어요() throws Exception {
        //given
        Long savedId = saveOneArticleAndGetId();
        Article findArticle = articleRepository.findById(savedId)
            .orElseThrow(() -> new Exception("cannot find"));
        assertThat(findArticle.getDislikes()).isEqualTo(0);

        //when
        Integer updatedDislike = articleRepository.minus(savedId);
        Article updateArticle = articleRepository.findById(savedId)
            .orElseThrow(() -> new Exception("cannot find"));

        //then
        assertThat(updateArticle.getDislikes()).isEqualTo(1);
        assertThat(updatedDislike).isEqualTo(1);
    }

    private void saveThreeArticle() {
        Member member = Member.createMember("승준", Gender.MALE, "010-1234-1234", "jdw@gmail.com", LocalDate.of(1990, 3, 21));

        Article article1 = Article.createArticle(member, "후라이 만드는법", "철수", "후라이팬에 기름을 두르고 굽는다");
        Article article2 = Article.createArticle(member, "일기", "영희", "오늘 날씨 맑음");
        Article article3 = Article.createArticle(member, "밥 먹을 사람?", "철수", "10분 뒤에 우리집으로");

        articleRepository.save(article1);
        articleRepository.save(article2);
        articleRepository.save(article3);
    }

    private Long saveOneArticleAndGetId() {
        Member member = Member.createMember("승준", Gender.MALE, "010-1234-1234", "jdw@gmail.com", LocalDate.of(1990, 3, 21));

        Article article = Article.createArticle(member, "아이디 추출용", "지석", "id 값이 필요합니다");
        return articleRepository.save(article);
    }

    private Long saveOneCommentAndGetArticleId() {
        Member member = Member.createMember("승준", Gender.MALE, "010-1234-1234", "jdw@gmail.com", LocalDate.of(1990, 3, 21));

        Article article = Article.createArticle(member, "아이디 추출용", "지석", "id 값이 필요합니다");
        Long articleId = articleRepository.save(article);

        Comment rootComment = Comment.createRootComment(article, "철수", "좋은 글 감사합니다");
        commentRepository.save(rootComment);
        return articleId;
    }
}
