package boardprogram.DTO;

import javax.persistence.Lob;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ArticleUpdateForm {

    private String title;

    @Lob
    private String content;

    public ArticleUpdateForm() {
    }

    public ArticleUpdateForm(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
