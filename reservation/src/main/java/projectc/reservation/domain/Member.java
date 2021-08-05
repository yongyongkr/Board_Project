package projectc.reservation.domain;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Member {

    @Id
    @Column(name = "MEMBER_ID")
    private String id;

    private String name;
    private int age;

    @OneToMany(mappedBy = "Reservation")
    private List<Reservation> reservations;
}
