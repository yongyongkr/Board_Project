package projectc.reservation.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import projectc.reservation.domain.Movie;
import projectc.reservation.domain.Rate;
import projectc.reservation.domain.ScreeningInfo;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class ScreeningInfoRepositoryTest {

    @Autowired
    ScreeningInfoRepository screeningInfoRepository;
    @Autowired
    MovieRepository movieRepository;

    @Test
    public void 상영정보등록() throws Exception {
        //given
        Movie movie1 = Movie.createMovie("MissionImpossible", 105, Rate.All);
        movieRepository.save(movie1);

        ScreeningInfo screeningInfo = ScreeningInfo.createScreeningInfo(150,
            LocalDateTime.of(2021, 8, 7, 2, 0, 0),
            LocalDateTime.of(2021, 8, 7, 3, 45, 0),
            15000, 10000, movie1);

        //when
        Long savedId = screeningInfoRepository.save(screeningInfo);
        ScreeningInfo findScreeningInfo = screeningInfoRepository.findOne(savedId);

        //then
        assertThat(150).isEqualTo(findScreeningInfo.getRemnant());
        assertThat(LocalDateTime.of(2021, 8, 7, 3, 45, 0))
            .isEqualTo(findScreeningInfo.getEndTime());

        assertThat(movie1.getFilmRating()).isEqualTo(findScreeningInfo.getMovie().getFilmRating());
    }

    @Test
    public void 상영정보삭제() throws Exception {
        //given
        Movie movie1 = Movie.createMovie("MissionImpossible", 105, Rate.All);
        Long savedMovieId = movieRepository.save(movie1);

        ScreeningInfo screeningInfo = ScreeningInfo.createScreeningInfo(150,
            LocalDateTime.of(2021, 8, 7, 2, 0, 0),
            LocalDateTime.of(2021, 8, 7, 3, 45, 0),
            15000, 10000, movie1);
        Long savedScreeningInfoId = screeningInfoRepository.save(screeningInfo);

        //when
        screeningInfoRepository.delete(savedScreeningInfoId); //주석일 때는 아래 expected 1

        //then
        assertThat(0).isEqualTo(screeningInfoRepository.findAll().size());

        assertThat(movieRepository.findOne(savedMovieId).getScreeningInfos().size()).isEqualTo(0);

    }
}