package project.b.guest.book.article;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String username;
    private String content;
    private Timestamp uploadTime;
    private Timestamp updateTime;
    private int likes;
    private int dislikes;

    protected Article(String title, String username, String content) {
        this.title = title;
        this.username = username;
        this.content = content;
    }

    public static Article createArticle(String title, String username, String content) {
        Article article = new Article(title, username, content);
        return article;
    }
}
