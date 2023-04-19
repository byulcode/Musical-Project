package study.musical.domain.likes.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.musical.domain.musical.entity.Musical;
import study.musical.domain.member.entity.Member;
import study.musical.infra.entity.BaseTimeEntity;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "likes")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Likes extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "musical_id")
    private Musical musical;

    @Builder
    public Likes(Member member, Musical musical) {
        this.member = member;
        this.musical = musical;
    }
}
