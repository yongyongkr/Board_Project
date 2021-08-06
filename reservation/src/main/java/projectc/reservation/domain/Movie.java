package projectc.reservation.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Getter;

@Entity
@Getter
public class Movie {

    @Id
    @GeneratedValue
    @Column(name = "MOVIE_ID")
    private Long id;

    private String title;
    private int runningTime;

    @Enumerated(EnumType.STRING)
    private Rate filmRating;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<ScreeningInfo> screeningInfos = new ArrayList<>();

    protected Movie() {
    }

    protected Movie(Long id, String title, int runningTime, Rate filmRating) {
        this.id = id;
        this.title = title;
        this.runningTime = runningTime;
        this.filmRating = filmRating;
    }

    public static Movie createMovie(Long id, String title, int runningTime,
        Rate filmRating) {
        Movie movie = new Movie(id, title, runningTime, filmRating);
        return movie;
    }
}
