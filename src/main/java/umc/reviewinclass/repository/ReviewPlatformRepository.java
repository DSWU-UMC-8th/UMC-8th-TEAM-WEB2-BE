package umc.reviewinclass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.reviewinclass.domain.mapping.ReviewPlatform;

public interface ReviewPlatformRepository extends JpaRepository<ReviewPlatform, Long> {

}