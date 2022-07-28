package study.colibear.sdj.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import study.colibear.sdj.entity.Member;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class NamedMemberJpaRepositoryTest {

    @PersistenceContext
    EntityManager em;
    @Autowired
    private NamedMemberJpaRepository memberJpaRepository;
    @Test
    public void nameQuery() {

        Member a = Member.builder().username("a").age(10).build();
        Member b = Member.builder().username("b").age(20).build();
        em.persist(a);
        em.persist(b);

        List<Member> members = memberJpaRepository.findByUsername("a");

        assertThat(members.get(0).getUsername()).isEqualTo("a");
    }
}