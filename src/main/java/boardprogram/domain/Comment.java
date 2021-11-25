package boardprogram.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    private String username;

    @Lob
    private String content;

    @Enumerated(EnumType.STRING)
    private DeletedStatus isDeleted;

    private Integer likes;
    private Integer dislikes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Comment parent;

    @OneToMany(mappedBy = "parent")
    private List<Comment> children = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;

    public void setArticleRelation(Article article) {
        this.article = article;
    }

    private void setCommentRelation(Comment parent) {
        this.parent = parent;
        parent.getChildren().add(this);
    }

    protected Comment(String username, String content) {
        this.username = username;
        this.content = content;
        this.likes = 0;
        this.dislikes = 0;
    }

    public static Comment createRootComment(Article article, String username, String content) {
        Comment comment = new Comment(username, content);
        comment.setArticleRelation(article);
        return comment;
    }

    public static Comment createLeafComment(Article article, Comment parent, String username,
        String content) {
        Comment comment = new Comment(username, content);
        comment.setArticleRelation(article);
        comment.setCommentRelation(parent);
        return comment;
    }
}
