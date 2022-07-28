package study.colibear.sdj.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import study.colibear.sdj.entity.Member;

import javax.transaction.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class MemberJpaRepositoryTest {

    @Autowired
    MemberJpaRepository memberJpaRepository;

    @Test
    @Rollback(value = false)
    void save() {
        Member member = Member.builder()
            .username("memberA")
            .age(10)
            .build();

        Member savedMember = memberJpaRepository.save(member);

        Member findMember = memberJpaRepository.find(savedMember.getId());

        assertThat(findMember.getId()).isEqualTo(member.getId());
        assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        assertThat(findMember).isEqualTo(member);
    }

    @Test
    public void findByUsernameAndAgegt() {
        Member a = Member.builder().username("a").age(10).build();
        Member b = Member.builder().username("b").age(20).build();

        memberJpaRepository.save(a);
        memberJpaRepository.save(b);

        List<Member> members = memberJpaRepository.findByUsernameAndAgeGt("b", 15);
        Member findMember = members.get(0);
        assertThat(findMember.getUsername()).isEqualTo("b");
        assertThat(findMember.getAge()).isEqualTo(20);

        assertThat(members.size()).isEqualTo(1);
    }
}