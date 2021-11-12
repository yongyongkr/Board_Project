package boardprogram.service;

import boardprogram.domain.Article;
import boardprogram.repository.ArticleRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ArticleService {

    private ArticleRepository articleRepository;

    public void upload(Article article) {
        articleRepository.save(article);
    }

    public List<Article> articleList() {
        return articleRepository.findAll();
    }

    public void deleteByDislikes(Article article) {
        if (article.getDislikes() >= 10) {
            articleRepository.delete(article.getId());
        }
    }

    public void likes(Long id) {
        articleRepository.plus(id);
    }

    public void dislikes(Long id) {
        articleRepository.minus(id);
    }
}
