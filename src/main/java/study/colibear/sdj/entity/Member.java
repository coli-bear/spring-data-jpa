package study.colibear.sdj.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter // 실무에서는 사용하지 않는거 권장
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "username", "age"})
@AllArgsConstructor
@Builder
public class Member {
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String username;
    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }
}