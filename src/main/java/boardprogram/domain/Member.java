package boardprogram.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id")
	private Long id;

	@NotBlank
	private String username;

	@NotNull
	private Gender gender;

	@NotNull
	private String phoneNumber;

	@Email
	@NotBlank
	private String email;

	@Past
	@NotNull
	private LocalDate birthday;

	protected Member(String username, Gender gender, String phoneNumber, String email, LocalDate birthday) {
		this.username = username;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.birthday = birthday;
	}

	public static Member createMember(String username, Gender gender, String phoneNumber, String email,
		LocalDate birthday) {
		return new Member(username, gender, phoneNumber, email, birthday);
	}
}
