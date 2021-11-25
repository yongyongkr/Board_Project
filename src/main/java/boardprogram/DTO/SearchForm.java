package boardprogram.DTO;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchForm {

    @Enumerated(EnumType.STRING)
    private SearchType searchType;

    private String keyword;

    public SearchForm() {
    }

    public SearchForm(SearchType searchType, String keyword) {
        this.searchType = searchType;
        this.keyword = keyword;
    }
}
