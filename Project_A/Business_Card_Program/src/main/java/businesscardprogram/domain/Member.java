package businesscardprogram.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String number;
    private String company;

    private Member(String name, String number, String company) {
        this.name = name;
        this.number = number;
        this.company = company;
    }

    public Member createMember(String name, String number, String company) {
        Member member = new Member(name, number, company);
        return member;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getCompany() {
        return company;
    }
}
