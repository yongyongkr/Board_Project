package projectc.reservation.domain;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Reservation {

    @Id
    @Column(name = "RESERVATION_ID")
    private String id;

    private int totalPrice;
    private int adultCount;
    private int childCount;
    private LocalDateTime reserveTime;

    private Status reservationStatus;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "SCREENINGINFO_ID")
    private ScreeningInfo screeningInfo;

}
