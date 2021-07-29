package project.b.guest.book.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import project.b.guest.book.article.Article;

public class MemoryArticleRepository implements ArticleRepository {

    private static Map<Long, Article> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Article save(Article article) {
        article.setId(++sequence);
        article.setTime(System.currentTimeMillis());
        article.setLikes(0L);
        article.setDislikes(0L);
        store.put(article.getId(), article);
        return article;
    }

    @Override
    public Optional<Article> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Article> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void delete(Long id) {
        store.remove(id);
    }

    @Override
    public void plus(Long id) {
        Article article1 = store.get(id);
        Long likes = article1.getLikes() + 1;
        article1.setLikes(likes);
    }

    @Override
    public void minus(Long id) {
        Article article1 = store.get(id);
        Long dislikes = article1.getDislikes() + 1;
        article1.setDislikes(dislikes);
    }

    @Override
    public boolean existOrNot(Article article) {
        return store.values().stream()
            .filter(a -> a.getName().equals(article.getName()))
            .findAny().isPresent();
    }
}
