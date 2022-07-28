package study.colibear.sdj.entity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

@SpringBootTest
@Transactional
class MemberTest {

    @PersistenceContext
    EntityManager em;

    @Test
    public void testEntity() {
        Team teamA = Team.builder().name("teamA").build();
        Team teamB = Team.builder().name("teamB").build();
        em.persist(teamA);
        em.persist(teamB);

        Member member1 = Member.builder().username("member1").age(10).build();
        Member member2 = Member.builder().username("member2").age(20).build();
        Member member3 = Member.builder().username("member3").age(30).build();
        Member member4 = Member.builder().username("member4").age(40).build();

        member1.changeTeam(teamA);
        member2.changeTeam(teamA);
        member3.changeTeam(teamB);
        member4.changeTeam(teamB);

        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);

        em.flush();
        em.clear();

        List<Member> members = em.createQuery("select m from Member m" , Member.class).getResultList();

        for (Member member : members) {
            System.out.println(member.getTeam().getName());
        }
    }
}