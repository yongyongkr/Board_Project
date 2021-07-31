package project.b.guest.book.repository;

import java.util.ArrayList;
import java.util.Comparator;
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
        article.setLikes(0);
        article.setDislikes(0);
        if (validateTime(article) < 60)
            store.put(article.getId(), article);
        return article;
    }

    @Override
    public Optional<Article> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Long> findByName(String name) {
        return store.values().stream()
            .filter(a -> a.getName().equals(name))
            .map(Article::getTime).sorted(Comparator.reverseOrder()).findFirst();
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
        int likes = article1.getLikes() + 1;
        article1.setLikes(likes);
    }

    @Override
    public void minus(Long id) {
        Article article1 = store.get(id);
        int dislikes = article1.getDislikes() + 1;
        article1.setDislikes(dislikes);
    }

    @Override
    public boolean existOrNot(Article article) {
        return store.values().stream()
            .filter(a -> a.getName().equals(article.getName()))
            .findAny().isPresent();
    }

    @Override
    public long validateTime(Article article) {
        if (existOrNot(article)) {
            long lastTime = findByName(article.getName()).get();
            return (article.getTime() - lastTime) / 1000;
        }
        return 0l;
    }

    public void clearStore() {
        store.clear();
    }
}
