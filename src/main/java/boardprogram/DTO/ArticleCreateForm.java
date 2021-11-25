package boardprogram.DTO;

import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ArticleCreateForm {

    @NotBlank
    private String title;

    @NotBlank
    private String username;

    @Lob
    @NotBlank
    private String content;

    public ArticleCreateForm() {
    }

    public ArticleCreateForm(String title, String username, String content) {
        this.title = title;
        this.username = username;
        this.content = content;
    }
}
