package boardprogram.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "article_id")
	private Long id;

	private String title;
	private String username;

	@Lob
	private String content;

	private Integer view;
	private Integer likes;
	private Integer dislikes;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	protected Article(String title, String username, String content) {
		this.title = title;
		this.username = username;
		this.content = content;
		this.view = 1;
		this.likes = 0;
		this.dislikes = 0;
	}

	public static Article createArticle(Member member, String title, String username, String content) {
		Article article = new Article(title, username, content);
		article.setMemberRelation(member);
		return article;
	}

	public static Article updateArticle(Article article, String title, String content) {
		article.setTitle(title);
		article.setContent(content);
		return article;
	}

	public void setMemberRelation(Member member) {
		this.member = member;
	}

	private void setTitle(String title) {
		this.title = title;
	}

	private void setContent(String content) {
		this.content = content;
	}

	public void addView() {
		this.view = this.view + 1;
	}
}
