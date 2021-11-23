package boardprogram.DTO;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommentDTO {

    @NotBlank
    private String username;

    @NotBlank
    private String content;

    public CommentDTO() {
    }

    public CommentDTO(String username, String content) {
        this.username = username;
        this.content = content;
    }
}
