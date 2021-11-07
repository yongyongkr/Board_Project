package boardprogram.domain;

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
public class Like {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long likes;
    private Long dislikes;

    protected Like(Long likes, Long dislikes) {
        this.likes = likes;
        this.dislikes = dislikes;
    }

    public static Like createLike() {
        Like like = new Like(0L, 0L);
        return like;
    }
}
