package umc.reviewinclass.service.LectureService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.reviewinclass.apiPayload.code.status.ErrorStatus;
import umc.reviewinclass.apiPayload.exception.handler.LectureHandler;
import umc.reviewinclass.domain.lecture.Lecture;
import umc.reviewinclass.domain.lecture.LectureImage;
import umc.reviewinclass.domain.platform.Platform;
import umc.reviewinclass.domain.review.Review;
import umc.reviewinclass.repository.LectureRepository;
import umc.reviewinclass.repository.ReviewRepository;
import umc.reviewinclass.web.dto.lecture.LectureRatingSummaryDto;
import umc.reviewinclass.web.dto.lecture.LectureResponseDTO;
import umc.reviewinclass.web.dto.lecture.LectureSearchResponseDTO;

import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LectureQueryServiceImpl implements LectureQueryService {

    private final LectureRepository lectureRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public LectureResponseDTO.LectureDTO getLectureInfo(Long lectureId) {

        // 강의 찾기
        Lecture lecture = lectureRepository.findById(lectureId)
                .orElseThrow(() -> new LectureHandler(ErrorStatus.LECTURE_NOT_FOUND));

        // platform
        Platform platform = lecture.getPlatform();

        // 강의 썸네일 리스트
        List<String> imgUrls = lecture.getLectureImages().stream()
                .map(LectureImage::getLectureImageUrl)
                .toList();

        return LectureResponseDTO.LectureDTO.builder()
                .name(lecture.getName())
                .instructorName(lecture.getInstructorName())
                .level(lecture.getLevel())
                .category(lecture.getCategory())
                .platformName(platform.getName())
                .imgUrls(imgUrls)
                .build();
    }

    /**
     * 강의를 강의명으로 검색합니다.
     *
     * @param query
     * @return LectureDTO list
     */
    public List<LectureSearchResponseDTO.LectureDTO> search(String query) {
        List<LectureSearchResponseDTO.LectureDTO> lectures = lectureRepository.findByNameContaining(query).stream()
                .map(p -> LectureSearchResponseDTO.LectureDTO.builder()
                        .id(p.getLectureId())
                        .name(p.getName())
                        .instructor(p.getInstructorName())
                        .platform(p.getPlatform().getName())
                        .build())
                .toList();
        return lectures;
    }

    /**강의별 평점 & 별점 개수 조회**/
    @Override
    public LectureRatingSummaryDto getLectureRatingSummary(Long lectureId) {
        Lecture lecture = lectureRepository.findById(lectureId)
                .orElseThrow(() -> new LectureHandler(ErrorStatus.LECTURE_NOT_FOUND));

        List<Review> reviews = reviewRepository.findAllByLecture(lecture);

        if (reviews.isEmpty()) {
            return new LectureRatingSummaryDto(0.0, 0, Map.of(5, 0L, 4, 0L, 3, 0L, 2, 0L, 1, 0L));
        }

        double average = reviews.stream()
                .mapToDouble(Review::getRating)
                .average()
                .orElse(0.0);

        Map<Integer, Long> distribution = reviews.stream()
                .collect(Collectors.groupingBy(
                        r -> (int) Math.round(r.getRating()), // 4.5 → 5, 3.6 → 4
                        Collectors.counting()
                ));

        // 모든 점수 보장 (5~1)
        for (int i = 1; i <= 5; i++) {
            distribution.putIfAbsent(i, 0L);
        }

        return LectureRatingSummaryDto.builder()
                .averageRating(Math.round(average * 100.0) / 100.0)
                .reviewCount(reviews.size())
                .ratingDistribution(distribution.entrySet().stream()
                        .sorted((e1, e2) -> Integer.compare(e2.getKey(), e1.getKey())) // 내림차순 정렬
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                                (e1, e2) -> e1, LinkedHashMap::new)))
                .build();
    }

}
