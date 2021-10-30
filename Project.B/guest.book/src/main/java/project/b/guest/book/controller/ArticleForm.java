package project.b.guest.book.controller;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class ArticleForm {

    private String name;
    private String context;
}
