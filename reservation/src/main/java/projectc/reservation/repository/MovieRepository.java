package projectc.reservation.repository;

import java.util.List;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import projectc.reservation.domain.Member;
import projectc.reservation.domain.Movie;

@Repository
@RequiredArgsConstructor
public class MovieRepository {

    private final EntityManager em;

    public Long save(Movie movie) {
        em.persist(movie);
        return movie.getId();
    }

    public void delete(Long id) {
        Movie findMovie = em.find(Movie.class, id);
        em.remove(findMovie);
    }

    public Movie findOne(Long id) {
        return em.find(Movie.class, id);
    }

    public List<Movie> findAll() {
        return em.createQuery("select m from Movie m", Movie.class)
            .getResultList();
    }
}
