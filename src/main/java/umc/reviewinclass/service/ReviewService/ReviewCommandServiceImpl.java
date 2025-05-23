package umc.reviewinclass.service.ReviewService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.reviewinclass.domain.enums.StudyPeriod;
import umc.reviewinclass.domain.lecture.Lecture;
import umc.reviewinclass.domain.mapping.ReviewPlatform;
import umc.reviewinclass.domain.platform.Platform;
import umc.reviewinclass.domain.review.Review;
import umc.reviewinclass.repository.ReviewPlatformRepository;
import umc.reviewinclass.web.dto.review.ReviewCreateRequestDTO;
import umc.reviewinclass.repository.LectureRepository;
import umc.reviewinclass.repository.PlatformRepository;
import umc.reviewinclass.repository.ReviewRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final ReviewRepository reviewRepository;
    private final LectureRepository lectureRepository;
    private final PlatformRepository platformRepository;
    private final ReviewPlatformRepository reviewPlatformRepository;

    /**
     * 리뷰를 등록하고, 리뷰-플랫폼 매핑 데이터를 저장합니다.
     *
     * @param requestDto
     * @return 생성된 리뷰의 Id
     * @throws IllegalArgumentException 강의를 찾을 수 없는 경우
     */
    @Override
    @Transactional
    public Long createReview(ReviewCreateRequestDTO requestDto) {
        Lecture lecture = lectureRepository.findById(requestDto.getLectureId())
                .orElseThrow(() -> new IllegalArgumentException("강의를 찾을 수 없습니다."));

        StudyPeriod studyPeriod = StudyPeriod.valueOf(requestDto.getStudyPeriod());

        Review review = Review.builder()
                .lecture(lecture)
                .rating(requestDto.getRating())
                .content(requestDto.getContent())
                .reviewImageUrl(requestDto.getImageUrl())
                .studyPeriod(studyPeriod)
                .likes(0L)
                .build();

        Review savedReview = reviewRepository.save(review);

        List<Platform> platforms = platformRepository.findAllById(requestDto.getPlatformIds());
        for (Platform platform : platforms) {
            reviewPlatformRepository.save(ReviewPlatform.builder()
                    .review(savedReview)
                    .platform(platform)
                    .build());
        }

        return savedReview.getReviewId();
    }
}

