package boardprogram.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommentDTO {

    private String username;
    private String content;

    public CommentDTO() {
    }

    public CommentDTO(String username, String content) {
        this.username = username;
        this.content = content;
    }
}
