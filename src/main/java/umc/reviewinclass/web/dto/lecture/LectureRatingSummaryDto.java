package umc.reviewinclass.web.dto.lecture;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LectureRatingSummaryDto {
    private double averageRating;
    private long reviewCount;
    private Map<Integer, Long> ratingDistribution; // key: 평점, value: 개수 (5~1)
}
