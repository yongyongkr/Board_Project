package projectc.reservation.repository;

import java.util.List;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import projectc.reservation.domain.Reservation;
import projectc.reservation.domain.ScreeningInfo;

@Repository
@RequiredArgsConstructor
public class ReservationRepository {

    private final EntityManager em;

    public String save(Reservation reservation) {
        em.persist(reservation);
        return reservation.getId();
    }

    public Reservation findOne(String id) {
        return em.find(Reservation.class, id);
    }

    public List<Reservation> findAll() {
        return em.createQuery("select r from Reservation r", Reservation.class)
            .getResultList();
    }
}
