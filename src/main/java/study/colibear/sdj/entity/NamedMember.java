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
@NamedQuery(
    name = "Member.findByUsername",
    query = "select m from NamedMember m where m.username = :username"
)
public class NamedMember {
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String username;
    private int age;
}