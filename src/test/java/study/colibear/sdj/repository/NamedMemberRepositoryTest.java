package study.colibear.sdj.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import study.colibear.sdj.entity.Member;
import study.colibear.sdj.entity.NamedMember;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback(value = false)
class NamedMemberRepositoryTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    private NamedMemberRepository namedMemberRepository;

    @Test
    public void findNamedQuery() {
        Member a = Member.builder().username("a").age(10).build();
        Member b = Member.builder().username("b").age(20).build();
        em.persist(a);
        em.persist(b);

        List<NamedMember> members = namedMemberRepository.findByUsername("a");

        assertThat(members.get(0).getUsername()).isEqualTo("a");
    }

}