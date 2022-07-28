package study.colibear.sdj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.colibear.sdj.entity.Member;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
}