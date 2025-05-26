package umc.reviewinclass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.reviewinclass.domain.common.Uuid;

public interface UuidRepository extends JpaRepository<Uuid, Long> {
}
