package projectc.reservation.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name = "MOVIE")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MOVIE_ID")
    private Long id;

    private String title;
    private int runningTimeMinute;

    @Enumerated(EnumType.STRING)
    private Rate filmRating;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<ScreeningInfo> screeningInfos = new ArrayList<>();

    //==연관관계 메서드==//
    public void addScreeningInfo(ScreeningInfo screeningInfo) {
        this.getScreeningInfos().add(screeningInfo);
    }

    public void subtractScreeningInfo(ScreeningInfo screeningInfo) {
        this.getScreeningInfos().remove(screeningInfo);
    }

    protected Movie() {
    }

    protected Movie(String title, int runningTimeMinute, Rate filmRating) {
        this.title = title;
        this.runningTimeMinute = runningTimeMinute;
        this.filmRating = filmRating;
    }

    public static Movie createMovie(String title, int runningTimeMinute, Rate filmRating,
        ScreeningInfo... screeningInfos) {
        Movie movie = new Movie(title, runningTimeMinute, filmRating);
        for (ScreeningInfo screeningInfo : screeningInfos) {
            movie.addScreeningInfo(screeningInfo);
        }
        return movie;
    }
}
