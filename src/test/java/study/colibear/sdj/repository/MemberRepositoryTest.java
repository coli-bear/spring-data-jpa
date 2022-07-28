package study.colibear.sdj.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import study.colibear.sdj.dto.MemberDto;
import study.colibear.sdj.entity.Member;
import study.colibear.sdj.entity.Team;

import javax.transaction.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional
@Rollback(false)
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    TeamRepository teamRepository;

    @Test
    public void saveMember() {
        Member member = Member.builder()
            .username("memberA")
            .age(10)
            .build();
        Member savedMember = memberRepository.save(member);

        Member findMember = memberRepository.findById(savedMember.getId()).get();


        assertThat(findMember.getId()).isEqualTo(member.getId());
        assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        assertThat(findMember).isEqualTo(member);
    }

    @Test
    public void basicCRUD() {
        Member member1 = Member.builder().username("member1").age(10).build();
        Member member2 = Member.builder().username("member2").age(20).build();

        memberRepository.save(member1);
        memberRepository.save(member2);

        Member findMember1 = memberRepository.findById(member1.getId()).get();
        Member findMember2 = memberRepository.findById(member2.getId()).get();

        assertThat(findMember1).isEqualTo(member1);
        assertThat(findMember2).isEqualTo(member2);

        List<Member> all = memberRepository.findAll();

        assertThat(all.size()).isEqualTo(2);

        long count = memberRepository.count();
        assertThat(count).isEqualTo(2);

        memberRepository.delete(member1);
        memberRepository.delete(member2);

        long count2 = memberRepository.count();
        assertThat(count2).isEqualTo(0);
    }

    @Test
    public void findByUsernameAndAgeGreateThen() {

        Member a = Member.builder().username("a").age(10).build();
        Member b = Member.builder().username("b").age(20).build();

        memberRepository.save(a);
        memberRepository.save(b);

        List<Member> members = memberRepository.findByUsernameAndAgeGreaterThan("b", 15);
        Member findMember = members.get(0);
        assertThat(findMember.getUsername()).isEqualTo("b");
        assertThat(findMember.getAge()).isEqualTo(20);

        assertThat(members.size()).isEqualTo(1);
    }

    @Test
    public void queryTest() {
        Member a = Member.builder().username("a").age(10).build();
        Member b = Member.builder().username("b").age(20).build();

        memberRepository.save(a);
        memberRepository.save(b);

        List<Member> members = memberRepository.findUser("a", 10);
        Member findMember = members.get(0);
        assertThat(findMember).isEqualTo(a);
    }

    @Test
    public void findMemberDto() {

        Team team = Team.builder().name("teamA").build();
        teamRepository.save(team);

        Member m1 = Member.builder().username("a").age(10).build();
        memberRepository.save(m1);
        m1.changeTeam(team);


        List<MemberDto> memberDto = memberRepository.findMemberDto();

        for (MemberDto member : memberDto)
            System.out.println(member.toString());
    }

    @Test
    public void findMemberIn() {
        Member m1 = Member.builder().username("a").age(10).build();
        Member m2 = Member.builder().username("b").age(10).build();
        Member m3 = Member.builder().username("c").age(10).build();
        Member m4 = Member.builder().username("d").age(10).build();
        memberRepository.save(m1);
        memberRepository.save(m2);
        memberRepository.save(m3);
        memberRepository.save(m4);

        List<Member> members = memberRepository.findByNames(Arrays.asList("a", "b0"));
    }
}
