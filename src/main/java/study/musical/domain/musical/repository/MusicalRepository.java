package study.musical.domain.musical.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.musical.domain.musical.entity.Musical;

public interface MusicalRepository extends JpaRepository<Musical, Long>, MusicalRepositoryCustom {

}
