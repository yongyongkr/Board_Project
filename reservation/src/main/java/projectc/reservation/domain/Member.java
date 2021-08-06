package projectc.reservation.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name = "MEMBER")
public class Member {

    @Id
    @Column(name = "MEMBER_ID")
    private String id;

    private String name;
    private int age;

    @OneToMany(mappedBy = "member") //멤버가 없어지면 예약 내용도 없어지나? - 아니다
    private List<Reservation> reservations = new ArrayList<>();

    public void addReservation(Reservation reservation) {
        this.getReservations().add(reservation);
    }

    protected Member() {
    }

    protected Member(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    //==생성 메서드==//
    public static Member createMember(String id, String name, int age, Reservation... reservations) {
        Member member = new Member(id, name, age);
        for (Reservation reservation : reservations) {
            member.addReservation(reservation);
        }
        return member;
    }

}
