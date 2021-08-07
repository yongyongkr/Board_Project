package projectc.reservation.domain;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name = "RESERVATION")
public class Reservation {

    @Id
    @Column(name = "RESERVATION_ID")
    private String id;

    private int totalPrice;
    private int adultCount;
    private int childCount;
    private LocalDateTime reserveTime;

    @Enumerated(EnumType.STRING)
    private Status reservationStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SCREENINGINFO_ID")
    private ScreeningInfo screeningInfo;

    public int getTotalCount() {
        return adultCount + childCount;
    }

    public void cancelReservationStatus() {
        this.reservationStatus = Status.CANCELED;
    }

    //==연관관계 메서드==//
    public void setMember(Member member) {
        this.member = member;
        member.getReservations().add(this);
    }

    public void setScreeningInfo(ScreeningInfo screeningInfo) {
        this.screeningInfo = screeningInfo;
        screeningInfo.getReservations().add(this);
    }

    protected Reservation() {
    }

    protected Reservation(String id, int totalPrice, int adultCount, int childCount,
        LocalDateTime reserveTime, Status reservationStatus) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.adultCount = adultCount;
        this.childCount = childCount;
        this.reserveTime = reserveTime;
        this.reservationStatus = reservationStatus;
    }

    public static Reservation createReservation(String id, int totalPrice, int adultCount,
        int childCount, Member member, ScreeningInfo screeningInfo) {
        Reservation reservation = new Reservation(id, totalPrice, adultCount, childCount,
            LocalDateTime.now(), Status.RESERVED);
        reservation.setMember(member);
        reservation.setScreeningInfo(screeningInfo);
        return reservation;
    }
}
