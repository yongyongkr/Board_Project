package boardprogram.domain;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Lob
    private String content;

    private boolean deleted;
    private Integer likes;
    private Integer dislikes;
    private LocalDateTime createTime;
    private LocalDateTime lastModifiedTime;

    protected Comment(String content) {
        this.content = content;
        this.likes = 0;
        this.dislikes = 0;
    }

    public static Comment createComment(String content) {
        Comment comment = new Comment(content);
        return comment;
    }
}
