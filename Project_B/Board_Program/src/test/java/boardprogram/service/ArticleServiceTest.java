package boardprogram.service;

import static org.assertj.core.api.Assertions.assertThat;

import boardprogram.domain.Article;
import boardprogram.domain.Comment;
import java.util.List;
import javax.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class ArticleServiceTest {

    @Autowired
    ArticleService articleService;
    @Autowired
    CommentService commentService;

    @Test
    public void 업로드() throws Exception {
        //given
        saveThreeArticle();

        //then
        assertThat(articleService.findAllArticles().size()).isEqualTo(3);
    }

    @Test
    public void 게시글_전체조회() {
        //given
        saveThreeArticle();

        //when
        List<Article> articles = articleService.findAllArticles();

        //then
        assertThat(articles.get(0).getTitle()).isEqualTo("후라이 만드는법");
        assertThat(articles.get(1).getContent()).isEqualTo("오늘 날씨 맑음");
        assertThat(articles.get(2).getUsername()).isEqualTo("철수");
    }

    @Test
    public void 좋아요_싫어요() throws Exception {
        //given
        Long articleId = saveOneArticleAndGetId();
        Article article = articleService.findArticleById(articleId);
        assertThat(article.getLikes()).isEqualTo(0);
        assertThat(article.getDislikes()).isEqualTo(0);

        //when
        articleService.likes(articleId);
        articleService.dislikes(articleId);

        Article updatedArticle = articleService.findArticleById(articleId);

        //then
        assertThat(updatedArticle.getLikes()).isEqualTo(1);
        assertThat(updatedArticle.getDislikes()).isEqualTo(1);
    }

    @Test
    public void 싫어요_10개_게시글_자동삭제() {
        //given
        Long articleId = saveOneArticleAndGetId();
        assertThat(articleService.findAllArticles().size()).isEqualTo(1);

        //when
        for (int i = 0; i < 10; i++) {
            articleService.dislikes(articleId);
        }

        //then
        assertThat(articleService.findAllArticles().size()).isEqualTo(0);
    }

    @Test
    public void 싫어요_자동삭제시_댓글까지삭제() throws Exception {
        //given
        Long articleId = saveOneCommentAndGetArticleId();
        assertThat(articleService.findAllArticles().size()).isEqualTo(1);
        assertThat(commentService.findAllComments().size()).isEqualTo(1);

        //when
        for (int i = 0; i < 10; i++) {
            articleService.dislikes(articleId);
        }

        //then
        assertThat(articleService.findAllArticles().size()).isEqualTo(0);
        assertThat(commentService.findAllComments().size()).isEqualTo(0);
    }

    private void saveThreeArticle() {
        Article article1 = Article.createArticle("후라이 만드는법", "철수", "후라이팬에 기름을 두르고 굽는다");
        Article article2 = Article.createArticle("일기", "영희", "오늘 날씨 맑음");
        Article article3 = Article.createArticle("밥 먹을 사람?", "철수", "10분 뒤에 우리집으로");

        articleService.upload(article1);
        articleService.upload(article2);
        articleService.upload(article3);
    }

    private Long saveOneArticleAndGetId() {
        Article article = Article.createArticle("아이디 추출용", "지석", "id 값이 필요합니다");
        return articleService.upload(article);
    }

    private Long saveOneCommentAndGetArticleId() {
        Article article = Article.createArticle("아이디 추출용", "지석", "id 값이 필요합니다");
        Long articleId = articleService.upload(article);

        Comment rootComment = Comment.createRootComment(article, "철수", "좋은 글 감사합니다");
        commentService.upload(rootComment);
        return articleId;
    }
}
