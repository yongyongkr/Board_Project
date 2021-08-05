package projectc.reservation.domain;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ScreeningInfo {

    @Id
    @Column(name = "SCREENINGINFO_ID")
    private Long id;

    private int remnant;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int adultPrice;
    private int childPrice;

    @ManyToOne
    @JoinColumn(name = "MOVIE_ID")
    private Movie movie;

}
