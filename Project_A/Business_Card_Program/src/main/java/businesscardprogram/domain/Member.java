package businesscardprogram.domain;

public class Member {

    private Long id;
    private String name;
    private Long number;
    private String company;

    public Member(Long id, String name, Long number, String company) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.company = company;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
