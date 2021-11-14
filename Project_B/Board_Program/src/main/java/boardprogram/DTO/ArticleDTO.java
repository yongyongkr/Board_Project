package boardprogram.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleDTO {

    private String title;
    private String username;
    private String content;

    public ArticleDTO() {
    }

    public ArticleDTO(String title, String username, String content) {
        this.title = title;
        this.username = username;
        this.content = content;
    }
}
