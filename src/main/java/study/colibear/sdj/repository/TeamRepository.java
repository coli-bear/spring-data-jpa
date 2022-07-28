package study.colibear.sdj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.colibear.sdj.entity.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
