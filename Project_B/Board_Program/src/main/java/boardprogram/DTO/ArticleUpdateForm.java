package boardprogram.DTO;

import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ArticleUpdateForm {

    @NotBlank
    private String title;

    @Lob
    @NotBlank
    private String content;

    public ArticleUpdateForm() {
    }

    public ArticleUpdateForm(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
