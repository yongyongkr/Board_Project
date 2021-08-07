package projectc.reservation.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
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

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class ReservationRepositoryTest {

    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    ScreeningInfoRepository screeningInfoRepository;
    @Autowired
    MovieRepository movieRepository;

    @Test
    public void 예약() throws Exception {
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

        //when
        Reservation reservation = Reservation.createReservation(
            screeningInfo1.getStartTime() + member1.getId(),
            35000, 1, 2, member1, screeningInfo1);
        String savedReservationId = reservationRepository.save(reservation);

        //then
        Reservation findReservation = reservationRepository.findOne(savedReservationId);

        assertThat(reservation.getReservationStatus())
            .isEqualTo(findReservation.getReservationStatus());
        assertThat(reservation.getReserveTime()).isEqualTo(findReservation.getReserveTime());
        assertThat(reservation.getTotalCount()).isEqualTo(findReservation.getTotalCount());
    }

    @Test
    public void 예약전체조회() throws Exception {
        //given
        Member member1 = Member.createMember("hello123", "Brian", 24);
        memberRepository.save(member1);

        Member member2 = Member.createMember("nice123", "Tim", 32);
        memberRepository.save(member2);

        Movie movie1 = Movie.createMovie("MissionImpossible", 105, Rate.All);
        movieRepository.save(movie1);

        Movie movie2 = Movie.createMovie("Avengers", 210, Rate.OVER12);
        movieRepository.save(movie2);

        ScreeningInfo screeningInfo1 = ScreeningInfo.createScreeningInfo(150,
            LocalDateTime.of(2021, 8, 7, 2, 0, 0),
            LocalDateTime.of(2021, 8, 7, 3, 45, 0),
            15000, 10000, movie1);
        screeningInfoRepository.save(screeningInfo1);

        ScreeningInfo screeningInfo2 = ScreeningInfo.createScreeningInfo(300,
            LocalDateTime.of(2021, 8, 1, 2, 0, 0),
            LocalDateTime.of(2021, 8, 1, 3, 45, 0),
            12000, 8000, movie2);
        screeningInfoRepository.save(screeningInfo2);

        //when
        Reservation reservation1 = Reservation.createReservation(
            screeningInfo1.getStartTime() + member1.getId(),
            35000, 1, 2, member1, screeningInfo1);
        reservationRepository.save(reservation1);

        Reservation reservation2 = Reservation.createReservation(
            screeningInfo2.getStartTime() + member2.getId(),
            12000, 1, 0, member2, screeningInfo2);
        reservationRepository.save(reservation2);

        //then
        Assertions.assertEquals(reservationRepository.findAll().size(), 2);

    }
}