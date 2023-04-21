package study.musical.domain.member.entity;

import lombok.*;
import study.musical.domain.likes.entity.Likes;
import study.musical.infra.entity.BaseTimeEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @OneToMany(mappedBy = "member")
    @ToString.Exclude
    private Set<Likes> likes;

    @Builder
    public Member(Long id, String name, String email, String password, Set<Likes> likes) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.likes = likes;
    }
}
