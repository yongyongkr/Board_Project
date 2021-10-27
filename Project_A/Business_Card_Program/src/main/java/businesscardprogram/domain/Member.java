package businesscardprogram.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String number;
    private String company;

    protected Member(String name, String number, String company) {
        this.name = name;
        this.number = number;
        this.company = company;
    }

    public static Member createMember(String name, String number, String company) {
        Member member = new Member(name, number, company);
        return member;
    }
}
