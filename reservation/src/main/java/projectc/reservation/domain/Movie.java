package projectc.reservation.domain;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Movie {

    @Id @GeneratedValue
    @Column(name = "MOVIE_ID")
    private Long id;

    private String title;
    private int runningTime;

    @OneToMany(mappedBy = "Reservation")
    private List<ScreeningInfo> screeningInfos;

    private Rate filmRating;
}
