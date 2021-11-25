package boardprogram.service;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import boardprogram.domain.Article;
import boardprogram.domain.Comment;
import boardprogram.domain.Gender;
import boardprogram.domain.Member;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class CommentServiceTest {

	@Autowired
	ArticleService articleService;
	@Autowired
	CommentService commentService;

	@Test
	public void 업로드() throws Exception {
		//given
		Long articleId = saveManyComments();

		//then
		assertThat(commentService.findAllComments().size()).isEqualTo(8);
	}

	@Test
	public void 댓글_싫어요10개_자동삭제_내용만변경() throws Exception {
		//given
		Long commentId = saveOneRootCommentAndGetId();
		assertThat(commentService.findAllComments().size()).isEqualTo(1);
		assertThat(commentService.findByCommentId(commentId).getDislikes()).isEqualTo(0);
		assertThat(commentService.findByCommentId(commentId).getContent()).isEqualTo("좋은 글 감사합니다");

		//when
		for (int i = 0; i < 10; i++) {
			commentService.dislikes(commentId);
		}

		//then
		assertThat(commentService.findAllComments().size()).isEqualTo(1);
		assertThat(commentService.findByCommentId(commentId).getContent()).isEqualTo("삭제된 댓글입니다");
	}

	@Test
	public void 싫어요_대댓글_내용변경() throws Exception {
		//given
		Long leafCommentId = saveOneLeafCommentAndGetId();
		assertThat(commentService.findAllComments().size()).isEqualTo(2);
		assertThat(commentService.findByCommentId(leafCommentId).getContent()).isEqualTo("동감합니다");

		//when
		for (int i = 0; i < 10; i++) {
			commentService.dislikes(leafCommentId);
		}

		//then
		assertThat(commentService.findByCommentId(leafCommentId).getContent()).isEqualTo(
			"삭제된 댓글입니다");
		assertThat(commentService.findAllComments().size()).isEqualTo(2);
	}

	private Long saveManyComments() {
		Member member = Member.createMember("승준", Gender.MALE, "010-1234-1234", "jdw@gmail.com",
			LocalDate.of(1990, 3, 21));

		Article article = Article.createArticle(member, "아이디 추출용", "지석", "id 값이 필요합니다");
		articleService.upload(article);

		Comment rootComment1 = Comment.createRootComment(article, "철수", "좋은 글 감사합니다");
		commentService.upload(rootComment1);
		Comment leafComment1 = Comment.createLeafComment(article, rootComment1, "영희", "동감합니다");
		Comment leafComment2 = Comment.createLeafComment(article, rootComment1, "세찬", "인정합니다");
		Comment leafComment3 = Comment.createLeafComment(article, rootComment1, "영희", "저는 반대합니다");
		commentService.upload(leafComment1);
		commentService.upload(leafComment2);
		commentService.upload(leafComment3);

		Comment rootComment2 = Comment.createRootComment(article, "미애", "이건 아니죠");
		commentService.upload(rootComment2);
		Comment leafComment4 = Comment.createLeafComment(article, rootComment2, "철수", "왜요?");
		Comment leafComment5 = Comment.createLeafComment(article, rootComment2, "철수",
			"저는 맞는것 같은데요?");
		Comment leafComment6 = Comment.createLeafComment(article, rootComment2, "미애", "그냥요");
		commentService.upload(leafComment4);
		commentService.upload(leafComment5);
		commentService.upload(leafComment6);

		return article.getId();
	}

	private Long saveOneRootCommentAndGetId() {
		Member member = Member.createMember("승준", Gender.MALE, "010-1234-1234", "jdw@gmail.com",
			LocalDate.of(1990, 3, 21));

		Article article = Article.createArticle(member, "아이디 추출용", "지석", "id 값이 필요합니다");
		articleService.upload(article);

		Comment rootComment = Comment.createRootComment(article, "철수", "좋은 글 감사합니다");
		return commentService.upload(rootComment);
	}

	private Long saveOneLeafCommentAndGetId() {
		Member member = Member.createMember("승준", Gender.MALE, "010-1234-1234", "jdw@gmail.com",
			LocalDate.of(1990, 3, 21));

		Article article = Article.createArticle(member, "아이디 추출용", "지석", "id 값이 필요합니다");
		articleService.upload(article);

		Comment rootComment = Comment.createRootComment(article, "철수", "좋은 글 감사합니다");
		commentService.upload(rootComment);
		Comment leafComment = Comment.createLeafComment(article, rootComment, "영희", "동감합니다");
		return commentService.upload(leafComment);
	}
}
