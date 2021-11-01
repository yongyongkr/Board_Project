package project.b.guest.book.domain;

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
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String content;
    private boolean deleted;

    protected Comment(String content) {
        this.content = content;
    }

    public static Comment createComment(String content) {
        Comment comment = new Comment(content);
        return comment;
    }
}
