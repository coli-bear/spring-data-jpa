package study.colibear.sdj.repository;

import org.springframework.stereotype.Repository;
import study.colibear.sdj.entity.Member;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class NamedMemberJpaRepository {
    @PersistenceContext
    private EntityManager em;

    public List<Member> findByUsername(String username) {
        return em.createNamedQuery("Member.findByUsername", Member.class)
            .setParameter("username", username)
            .getResultList();
    }
}
