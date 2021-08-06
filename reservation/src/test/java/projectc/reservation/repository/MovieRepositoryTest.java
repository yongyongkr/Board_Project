package projectc.reservation.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import projectc.reservation.domain.Movie;
import projectc.reservation.domain.Rate;
import projectc.reservation.domain.ScreeningInfo;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class MovieRepositoryTest {

    @Autowired
    MovieRepository movieRepository;
    @Autowired
    ScreeningInfoRepository screeningInfoRepository;

    @Test
    public void 영화등록() throws Exception {
        //given
        Movie movie = Movie.createMovie("MissionImpossible", 105, Rate.All);

        //when
        Long savedId = movieRepository.save(movie);
        Movie findMovie = movieRepository.findOne(savedId);

        //then
        assertThat(105).isEqualTo(findMovie.getRunningTimeMinute());
        assertThat(Rate.All).isEqualTo(findMovie.getFilmRating());
    }

    @Test
    public void 영화삭제() throws Exception {
        //given
        Movie movie = Movie.createMovie("MissionImpossible", 105, Rate.All);
        Long savedId = movieRepository.save(movie);

        //when
        movieRepository.delete(5L); //주석 처리했을 때 expected : 1

        //then
        assertThat(0).isEqualTo(movieRepository.findAll().size());
    }

    @Test
    public void 영화삭제시_상영정보_모두삭제() throws Exception {
        //given
        Movie movie = Movie.createMovie("MissionImpossible", 105, Rate.All);
        Long savedMovieId = movieRepository.save(movie);

        ScreeningInfo screeningInfo1 = ScreeningInfo.createScreeningInfo(150,
            "2021-08-07T02:00:00", "2021-08-07T03:45:00",
            15000, 10000, movie);
        Long savedScreeningInfoId1 = screeningInfoRepository.save(screeningInfo1);

        ScreeningInfo screeningInfo2 = ScreeningInfo.createScreeningInfo(150,
            "2021-08-09T02:00:00", "2021-08-09T03:45:00",
            15000, 10000, movie);
        Long savedScreeningInfoId2 = screeningInfoRepository.save(screeningInfo2);

        //when
        assertThat(movie.getScreeningInfos().size()).isEqualTo(2);
        assertEquals(screeningInfo1.getMovie().getId(), screeningInfo2.getMovie().getId());

        movieRepository.delete(savedMovieId);

        //then
        assertThat(screeningInfoRepository.findAll().size()).isEqualTo(0);
    }

}