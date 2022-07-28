package study.colibear.sdj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import study.colibear.sdj.entity.Member;
import study.colibear.sdj.entity.NamedMember;

import java.util.List;

public interface NamedMemberRepository extends JpaRepository<NamedMember, Long> {


    @Query(name = "Member.findByUsername")
    List<NamedMember> findByUsername(@Param("username") String username);
}