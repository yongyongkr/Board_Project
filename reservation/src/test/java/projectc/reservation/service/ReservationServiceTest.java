package projectc.reservation.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.time.LocalDateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import projectc.reservation.domain.Member;
import projectc.reservation.domain.Movie;
import projectc.reservation.domain.Rate;
import projectc.reservation.domain.Reservation;
import projectc.reservation.domain.ScreeningInfo;
import projectc.reservation.repository.MemberRepository;
import projectc.reservation.repository.MovieRepository;
import projectc.reservation.repository.ScreeningInfoRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class ReservationServiceTest {

    @Autowired
    ReservationService reservationService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    ScreeningInfoRepository screeningInfoRepository;

    @Test
    public void 예약등록() throws Exception {
        //given
        Member member1 = Member.createMember("hello123", "Brian", 24);
        memberRepository.save(member1);

        Movie movie = Movie.createMovie("MissionImpossible", 105, Rate.All);
        movieRepository.save(movie);

        ScreeningInfo screeningInfo1 = ScreeningInfo.createScreeningInfo(150,
            LocalDateTime.of(2021, 8, 7, 2, 0, 0),
            LocalDateTime.of(2021, 8, 7, 3, 45, 0),
            15000, 10000, movie);
        screeningInfoRepository.save(screeningInfo1);

        Reservation reservation = Reservation.createReservation(
            screeningInfo1.getStartTime() + member1.getId(),
            35000, 1, 2, member1, screeningInfo1);

        //when
        String saveId = reservationService.enrollReservation(reservation);

        //then
        assertThat(reservationService.findReservation(saveId)).isEqualTo(reservation);
    }

    @Test(expected = IllegalArgumentException.class)
    public void 예약등록예외_좌석부족() throws Exception {
        //given
        Member member1 = Member.createMember("hello123", "Brian", 24);
        memberRepository.save(member1);

        Member member2 = Member.createMember("nice123", "Tim", 32);
        memberRepository.save(member2);

        Movie movie1 = Movie.createMovie("MissionImpossible", 105, Rate.All);
        movieRepository.save(movie1);

        ScreeningInfo screeningInfo1 = ScreeningInfo.createScreeningInfo(3,
            LocalDateTime.of(2021, 8, 7, 2, 0, 0),
            LocalDateTime.of(2021, 8, 7, 3, 45, 0),
            15000, 10000, movie1);
        screeningInfoRepository.save(screeningInfo1);

        //when
        Reservation reservation1 = Reservation.createReservation(
            screeningInfo1.getStartTime() + member1.getId(),
            35000, 1, 2, member1, screeningInfo1);
        reservationService.enrollReservation(reservation1);

        Reservation reservation2 = Reservation.createReservation(
            screeningInfo1.getStartTime() + member2.getId(),
            12000, 1, 0, member2, screeningInfo1);

        reservationService.enrollReservation(reservation2);

        //then
        fail("잔여석 부족 예외가 발생해야 한다.");
    }

    @Test
    public void 예약취소() throws Exception {
        //given
        Member member1 = Member.createMember("hello123", "Brian", 24);
        memberRepository.save(member1);

        Movie movie = Movie.createMovie("MissionImpossible", 105, Rate.All);
        movieRepository.save(movie);

        ScreeningInfo screeningInfo1 = ScreeningInfo.createScreeningInfo(150,
            LocalDateTime.of(2022, 8, 7, 2, 0, 0),
            LocalDateTime.of(2022, 8, 7, 3, 45, 0),
            15000, 10000, movie);
        screeningInfoRepository.save(screeningInfo1);

        Reservation reservation = Reservation.createReservation(
            screeningInfo1.getStartTime() + member1.getId(),
            35000, 1, 2, member1, screeningInfo1);
        String saveId = reservationService.enrollReservation(reservation);

        //when
        assertEquals(147, screeningInfo1.getRemnant());

        reservationService.cancelReservation(reservation);

        //then
        assertEquals(150, screeningInfo1.getRemnant());
    }

    @Test(expected = IllegalStateException.class)
    public void 예약취소예외_시작이후취소() throws Exception {
        //given
        Member member1 = Member.createMember("hello123", "Brian", 24);
        memberRepository.save(member1);

        Movie movie = Movie.createMovie("MissionImpossible", 105, Rate.All);
        movieRepository.save(movie);

        ScreeningInfo screeningInfo1 = ScreeningInfo.createScreeningInfo(150,
            LocalDateTime.of(2021, 8, 7, 2, 0, 0),
            LocalDateTime.of(2021, 8, 7, 3, 45, 0),
            15000, 10000, movie);
        screeningInfoRepository.save(screeningInfo1);

        Reservation reservation = Reservation.createReservation(
            screeningInfo1.getStartTime() + member1.getId(),
            35000, 1, 2, member1, screeningInfo1);
        String saveId = reservationService.enrollReservation(reservation);

        //when
        reservationService.cancelReservation(reservation);

        //then
        fail("환불이 불가하다는 예외가 발생해야 한다.");
    }
}