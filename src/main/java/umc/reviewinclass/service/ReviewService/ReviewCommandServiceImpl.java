package umc.reviewinclass.service.ReviewService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import umc.reviewinclass.apiPayload.code.status.ErrorStatus;
import umc.reviewinclass.apiPayload.exception.handler.ReviewHandler;
import umc.reviewinclass.aws.s3.AmazonS3Manager;
import umc.reviewinclass.domain.common.Uuid;
import umc.reviewinclass.domain.enums.StudyPeriod;
import umc.reviewinclass.domain.lecture.Lecture;
import umc.reviewinclass.domain.mapping.ReviewPlatform;
import umc.reviewinclass.domain.platform.Platform;
import umc.reviewinclass.domain.review.Review;
import umc.reviewinclass.repository.*;
import umc.reviewinclass.web.dto.review.ReviewCreateRequestDTO;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final ReviewRepository reviewRepository;
    private final LectureRepository lectureRepository;
    private final PlatformRepository platformRepository;
    private final ReviewPlatformRepository reviewPlatformRepository;
    private final AmazonS3Manager amazonS3Manager;
    private final UuidRepository uuidRepository;

    /**
     * 리뷰를 등록하고, 리뷰-플랫폼 매핑 데이터를 저장합니다.
     *
     * @param requestDto
     * @return 생성된 리뷰의 Id
     * @throws IllegalArgumentException 강의를 찾을 수 없는 경우
     */
    @Override
    @Transactional
    public Long createReview(ReviewCreateRequestDTO requestDto, MultipartFile image) {
        Lecture lecture = lectureRepository.findById(requestDto.getLectureId())
                .orElseThrow(() -> new IllegalArgumentException("강의를 찾을 수 없습니다."));

        StudyPeriod studyPeriod = StudyPeriod.valueOf(requestDto.getStudyPeriod());

        // S3에 이미지 업로드
        String imageUrl = null;
        if (image != null && !image.isEmpty()) {
            Uuid uuidEntity = uuidRepository.save(Uuid.builder()
                    .uuid(UUID.randomUUID().toString())
                    .build());

            String keyName = amazonS3Manager.generateReviewImageKeyName(uuidEntity);
            imageUrl = amazonS3Manager.uploadFile(keyName, image);
        }

        Review review = Review.builder()
                .lecture(lecture)
                .rating(requestDto.getRating())
                .content(requestDto.getContent())
                .reviewImageUrl(imageUrl)
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

    @Override
    @Transactional
    public void likeReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ReviewHandler(ErrorStatus.REVIEW_NOT_FOUND));
        review.increaseLikes();
    }


}

