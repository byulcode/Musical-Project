package study.musical.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.musical.domain.member.entity.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);
    Optional<Member> findByNameOrEmail(String name, String email);

    Boolean existsByEmail(String email);

    Boolean existsByName(String name);
}
