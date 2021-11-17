package boardprogram.DTO;

import javax.persistence.Lob;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ArticleCreateForm {

    private String title;
    private String username;

    @Lob
    private String content;

    public ArticleCreateForm() {
    }

    public ArticleCreateForm(String title, String username, String content) {
        this.title = title;
        this.username = username;
        this.content = content;
    }
}
