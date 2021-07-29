package project.b.guest.book;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import project.b.guest.book.repository.ArticleRepository;
import project.b.guest.book.repository.JpaArticleRepository;
import project.b.guest.book.repository.MemoryArticleRepository;
import project.b.guest.book.service.GuestBookService;

@Configuration
public class SpringConfig {

    private final DataSource dataSource;
    private final EntityManager em;

    public SpringConfig(DataSource dataSource, EntityManager em) {
        this.dataSource = dataSource;
        this.em = em;
    }

    @Bean
    public GuestBookService guestBookService() {
        return new GuestBookService(articleRepository());
    }

    @Bean
    public ArticleRepository articleRepository() {
        return new MemoryArticleRepository();
    }
}
