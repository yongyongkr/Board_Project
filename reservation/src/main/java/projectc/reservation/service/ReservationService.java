package projectc.reservation.service;

import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projectc.reservation.domain.Member;
import projectc.reservation.domain.Reservation;
import projectc.reservation.domain.ScreeningInfo;
import projectc.reservation.domain.Status;
import projectc.reservation.repository.ReservationRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public String enrollReservation(Reservation reservation) {
        validateRemnant(reservation);
        reservationRepository.save(reservation);
        return reservation.getId();
    }

    private void validateRemnant(Reservation reservation) {
        int count = reservation.getTotalCount();
        ScreeningInfo screeningInfo = reservation.getScreeningInfo();
        if (!screeningInfo.checkRemnant(count)) {
            throw new IllegalArgumentException("선택된 좌석 수가 잔여석보다 많습니다.");
        }
        screeningInfo.subtractRemnant(count);
    }

    public void cancelReservation(Reservation reservation) {
        validateCancelTime(reservation);
        reservation.cancelReservationStatus();

        int count = reservation.getTotalCount();
        ScreeningInfo screeningInfo = reservation.getScreeningInfo();
        screeningInfo.addRemnant(count);
    }

    private void validateCancelTime(Reservation reservation) {
        LocalDateTime startTime = reservation.getScreeningInfo().getStartTime();
        LocalDateTime reserveTime = reservation.getReserveTime();
        if (reserveTime.isAfter(startTime)) {
            throw new IllegalStateException("취소가 불가합니다.");
        }
    }

    @Transactional(readOnly = true)
    public Reservation findReservation(String reserveId) {
        return reservationRepository.findOne(reserveId);
    }

    @Transactional(readOnly = true)
    public List<Reservation> showAllReservation() {
        return reservationRepository.findAll();
    }
}
