package study.musical.domain.member.entity;

import lombok.*;
import study.musical.domain.likes.entity.Likes;
import study.musical.infra.entity.BaseTimeEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Table(name = "member")
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 45)
    private String name;
    @Column(nullable = false, length = 100, unique = true)
    private String email;
    @Column(nullable = false, length = 100)
    private String password;

    @Enumerated(EnumType.STRING)
    private RoleType role;

    public enum RoleType {
        USER, ADMIN
    }

    @OneToMany(mappedBy = "member")
    @ToString.Exclude
    private Set<Likes> likes;

    @Builder
    public Member(Long id, String name, String email, String password, RoleType role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
