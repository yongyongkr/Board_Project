package projectc.reservation.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name = "SCREENING_INFO")
public class ScreeningInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SCREENINGINFO_ID")
    private Long id;

    private int remnant;
    private String startTime;
    private String endTime;
    private int adultPrice;
    private int childPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MOVIE_ID")
    private Movie movie;

    @OneToMany(mappedBy = "screeningInfo")
    private List<Reservation> reservations = new ArrayList<>();

    //==연관관계 메서드==//
    public void setMovie(Movie movie) {
        this.movie = movie;
        movie.getScreeningInfos().add(this);
    }

    public void addReservations(Reservation reservation) {
        this.getReservations().add(reservation);
    }

    protected ScreeningInfo() {
    }

    protected ScreeningInfo(int remnant, String startTime, String endTime, int adultPrice,
        int childPrice) {
        this.remnant = remnant;
        this.startTime = startTime;
        this.endTime = endTime;
        this.adultPrice = adultPrice;
        this.childPrice = childPrice;
    }

    public static ScreeningInfo createScreeningInfo(int remnant, String startTime,
        String endTime, int adultPrice, int childPrice, Movie movie, Reservation... reservations) {
        ScreeningInfo screeningInfo = new ScreeningInfo(remnant, startTime, endTime, adultPrice,
            childPrice);
        screeningInfo.setMovie(movie);
        for (Reservation reservation : reservations) {
            screeningInfo.addReservations(reservation);
        }
        return screeningInfo;
    }
}

