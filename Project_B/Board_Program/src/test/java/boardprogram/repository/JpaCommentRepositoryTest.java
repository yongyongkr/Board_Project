package boardprogram.repository;

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
public class JpaCommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    ArticleRepository articleRepository;

    @Test
    public void 루트_및_리프_저장() {
        //given
        saveManyComments();

        //then
        assertThat(commentRepository.findAll().size()).isEqualTo(8);
    }

    @Test
    public void 루트_아이디로_조회() throws Exception {
        //given
        Long savedCommentId = saveOneRootCommentAndGetId();

        //when
        Comment findComment = commentRepository.findById(savedCommentId)
            .orElseThrow(() -> new Exception("cannot find"));

        //then
        assertThat(findComment.getContent()).isEqualTo("좋은 글 감사합니다");
    }

    @Test
    public void 리프_아이디로_조회() throws Exception {
        //given
        Long savedCommentId = saveOneLeafCommentAndGetId();

        //when
        Comment findComment = commentRepository.findById(savedCommentId)
            .orElseThrow(() -> new Exception("cannot find"));

        //then
        assertThat(findComment.getContent()).isEqualTo("동감합니다");
    }

    @Test
    public void 게시물로_조회() {
        //given
        Long savedArticleId = saveManyComments();

        //when
        List<Comment> findArticles = commentRepository.findByArticle(savedArticleId);

        //then
        assertThat(findArticles.size()).isEqualTo(commentRepository.findAll().size());
    }

    @Test
    public void 댓글_삭제시_삭제된댓글입니다() throws Exception {
        //given
        Long rootCommentId = saveOneRootCommentAndGetId();
        Long leafCommentId = saveOneLeafCommentAndGetId();

        //when
        commentRepository.delete(rootCommentId);
        commentRepository.delete(leafCommentId);
        Comment rootComment = commentRepository.findById(rootCommentId)
            .orElseThrow(() -> new Exception("cannot find"));
        Comment leafComment = commentRepository.findById(leafCommentId)
            .orElseThrow(() -> new Exception("cannot find"));

        //then
        assertThat(rootComment.getContent()).isEqualTo("삭제된 댓글입니다");
        assertThat(leafComment.getContent()).isEqualTo("삭제된 댓글입니다");
    }

    @Test
    public void 좋아요() throws Exception {
        //given
        Long rootCommentId = saveOneRootCommentAndGetId();
        Long leafCommentId = saveOneLeafCommentAndGetId();

        //when
        commentRepository.plus(rootCommentId);
        commentRepository.plus(leafCommentId);
        Comment rootComment = commentRepository.findById(rootCommentId)
            .orElseThrow(() -> new Exception("cannot find"));
        Comment leafComment = commentRepository.findById(leafCommentId)
            .orElseThrow(() -> new Exception("cannot find"));

        //then
        assertThat(rootComment.getLikes()).isEqualTo(1);
        assertThat(leafComment.getLikes()).isEqualTo(1);
    }

    @Test
    public void 싫어요() throws Exception {
        //given
        Long rootCommentId = saveOneRootCommentAndGetId();
        Long leafCommentId = saveOneLeafCommentAndGetId();

        //when
        commentRepository.minus(rootCommentId);
        commentRepository.minus(leafCommentId);
        Comment rootComment = commentRepository.findById(rootCommentId)
            .orElseThrow(() -> new Exception("cannot find"));
        Comment leafComment = commentRepository.findById(leafCommentId)
            .orElseThrow(() -> new Exception("cannot find"));

        //then
        assertThat(rootComment.getDislikes()).isEqualTo(1);
        assertThat(leafComment.getDislikes()).isEqualTo(1);
    }

    private Long saveManyComments() {
        Article article = Article.createArticle("아이디 추출용", "지석", "id 값이 필요합니다");
        articleRepository.save(article);

        Comment rootComment1 = Comment.createRootComment(article, "철수", "좋은 글 감사합니다");
        commentRepository.save(rootComment1);
        Comment leafComment1 = Comment.createLeafComment(article, rootComment1, "영희", "동감합니다");
        Comment leafComment2 = Comment.createLeafComment(article, rootComment1, "세찬", "인정합니다");
        Comment leafComment3 = Comment.createLeafComment(article, rootComment1, "영희", "저는 반대합니다");
        commentRepository.save(leafComment1);
        commentRepository.save(leafComment2);
        commentRepository.save(leafComment3);

        Comment rootComment2 = Comment.createRootComment(article, "미애", "이건 아니죠");
        commentRepository.save(rootComment2);
        Comment leafComment4 = Comment.createLeafComment(article, rootComment2, "철수", "왜요?");
        Comment leafComment5 = Comment.createLeafComment(article, rootComment2, "철수",
            "저는 맞는것 같은데요?");
        Comment leafComment6 = Comment.createLeafComment(article, rootComment2, "미애", "그냥요");
        commentRepository.save(leafComment4);
        commentRepository.save(leafComment5);
        commentRepository.save(leafComment6);

        return article.getId();
    }

    private Long saveOneRootCommentAndGetId() {
        Article article = Article.createArticle("아이디 추출용", "지석", "id 값이 필요합니다");
        articleRepository.save(article);

        Comment rootComment = Comment.createRootComment(article, "철수", "좋은 글 감사합니다");
        return commentRepository.save(rootComment);
    }

    private Long saveOneLeafCommentAndGetId() {
        Article article = Article.createArticle("아이디 추출용", "지석", "id 값이 필요합니다");
        articleRepository.save(article);

        Comment rootComment = Comment.createRootComment(article, "철수", "좋은 글 감사합니다");
        commentRepository.save(rootComment);
        Comment leafComment = Comment.createLeafComment(article, rootComment, "영희", "동감합니다");
        return commentRepository.save(leafComment);
    }
}