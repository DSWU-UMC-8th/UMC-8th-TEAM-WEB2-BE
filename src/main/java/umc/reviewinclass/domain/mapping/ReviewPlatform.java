package umc.reviewinclass.domain.mapping;

import jakarta.persistence.*;
import lombok.*;
import umc.reviewinclass.domain.platform.Platform;
import umc.reviewinclass.domain.review.Review;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewPlatform {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "review_id")
    private Review review;

    @ManyToOne
    @JoinColumn(name = "platform_id")
    private Platform platform;
}
