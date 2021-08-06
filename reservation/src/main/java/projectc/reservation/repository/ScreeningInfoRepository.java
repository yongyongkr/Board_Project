package projectc.reservation.repository;

import java.util.List;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import projectc.reservation.domain.Movie;
import projectc.reservation.domain.ScreeningInfo;

@Repository
@RequiredArgsConstructor
public class ScreeningInfoRepository {

    private final EntityManager em;

    public Long save(ScreeningInfo screeningInfo) {
        em.persist(screeningInfo);
        return screeningInfo.getId();
    }

    public void delete(Long id) {
        ScreeningInfo findScreeningInfo = em.find(ScreeningInfo.class, id);
        findScreeningInfo.getMovie().subtractScreeningInfo(findScreeningInfo);
        em.remove(findScreeningInfo);
    }

    public ScreeningInfo findOne(Long id) {
        return em.find(ScreeningInfo.class, id);
    }

    public List<ScreeningInfo> findAll() {
        return em.createQuery("select s from ScreeningInfo s", ScreeningInfo.class)
            .getResultList();
    }
}
