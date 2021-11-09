package boardprogram.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Long id;

    private String title;
    private String username;

    @Lob
    private String content;

    private Integer likes;
    private Integer dislikes;
    private LocalDateTime createTime;
    private LocalDateTime lastModifiedTime;

    protected Article(String title, String username, String content) {
        this.title = title;
        this.username = username;
        this.content = content;
        this.likes = 0;
        this.dislikes = 0;
    }

    public static Article createArticle(String title, String username, String content) {
        Article article = new Article(title, username, content);
        return article;
    }
}
