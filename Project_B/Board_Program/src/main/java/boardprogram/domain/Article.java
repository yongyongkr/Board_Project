package boardprogram.domain;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private LocalDateTime createTime;
    private LocalDateTime lastModifiedTime;

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
