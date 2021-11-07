/*
package project.b.guest.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import project.b.guest.book.article.Article;
import boardprogram.service.GuestBookService;

@Controller
public class HomeController {

    private final GuestBookService guestBookService;

    @Autowired
    public HomeController(GuestBookService guestBookService) {
        this.guestBookService = guestBookService;
    }

    @GetMapping("/")
    public String homepage() {
        return "home";
    }

    @PostMapping("/")
    public String posting(ArticleForm form) {
        Article article = new Article();
        article.setName(form.getName());
        article.setContent(form.getContent());

        guestBookService.upload(article);

        return "home";
    }

}
*/
