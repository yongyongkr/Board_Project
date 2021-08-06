package projectc.reservation.domain;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;

@Entity
@Getter
public class ScreeningInfo {

    @Id
    @Column(name = "SCREENINGINFO_ID")
    private Long id;

    private int remnant;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int adultPrice;
    private int childPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MOVIE_ID")
    private Movie movie;

    //==연관관계 메서드==//
    public void setMovie(Movie movie) {
        this.movie = movie;
        movie.getScreeningInfos().add(this);
    }

    protected ScreeningInfo() {
    }

    protected ScreeningInfo(Long id, int remnant, LocalDateTime startTime, LocalDateTime endTime,
        int adultPrice, int childPrice) {
        this.id = id;
        this.remnant = remnant;
        this.startTime = startTime;
        this.endTime = endTime;
        this.adultPrice = adultPrice;
        this.childPrice = childPrice;
    }

    public static ScreeningInfo createScreeningInfo(Long id, int remnant, LocalDateTime startTime,
        LocalDateTime endTime, int adultPrice, int childPrice, Movie movie) {
        ScreeningInfo screeningInfo = new ScreeningInfo(id, remnant, startTime, endTime, adultPrice,
            childPrice);
        screeningInfo.setMovie(movie);
        return screeningInfo;
    }
}

