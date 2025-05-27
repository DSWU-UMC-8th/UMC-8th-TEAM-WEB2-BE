package umc.reviewinclass.service.ReviewService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import umc.reviewinclass.repository.ReviewRepository;
import umc.reviewinclass.web.dto.review.ReviewSearchListResponseDTO;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;
import umc.reviewinclass.apiPayload.code.status.ErrorStatus;
import umc.reviewinclass.apiPayload.exception.handler.LectureHandler;
import umc.reviewinclass.apiPayload.exception.handler.ReviewHandler;
import umc.reviewinclass.converter.ReviewConverter;
import umc.reviewinclass.domain.enums.CategoryType;
import umc.reviewinclass.domain.enums.Level;
import umc.reviewinclass.domain.enums.StudyPeriod;
import umc.reviewinclass.domain.lecture.Lecture;
import umc.reviewinclass.domain.review.Review;
import umc.reviewinclass.repository.LectureRepository;
import umc.reviewinclass.web.dto.review.*;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewQueryServiceImpl implements ReviewQueryService {

    private final ReviewRepository reviewRepository;
    private final LectureRepository lectureRepository;

    // 리뷰 검색
    @Override
    public Page<ReviewSearchListResponseDTO.ReviewDTO> searchReviews(
            String query, String sort, Double minRating, Double maxRating, Pageable pageable
    ) {
        return reviewRepository.searchReviews(query, sort, minRating, maxRating, pageable);
    }

    @Override
    public Page<PopularReviewResponseDTO> getPopularReviews(Pageable pageable) {
        Page<Review> reviews = reviewRepository.findAll(pageable);
        if (reviews.isEmpty()) throw new ReviewHandler(ErrorStatus.REVIEW_NOT_FOUND);
        return reviews.map(ReviewConverter::toPopularDTO);
    }

    @Override
    public Page<LatestReviewResponseDTO> getLatestReviews(Pageable pageable) {
        Page<Review> reviews = reviewRepository.findAll(pageable);
        if (reviews.isEmpty()) throw new ReviewHandler(ErrorStatus.REVIEW_NOT_FOUND);
        return reviews.map(ReviewConverter::toLatestDTO);
    }

    @Override
    public List<PopularReviewResponseDTO> getFilteredReviews(CategoryType category, Level level, StudyPeriod period) {
        List<Review> reviews = reviewRepository.filterReviews(category, level, period);
        if (reviews.isEmpty()) {
            throw new ReviewHandler(ErrorStatus.REVIEW_NOT_FOUND);
        }
        return ReviewConverter.toPopularDTOList(reviews);
    }

    @Override
    public ReviewListDTO getLectureReviews(Long lectureId, Double ratingMin, Double ratingMax, String sortField, int page) {
        Lecture lecture = lectureRepository.findById(lectureId)
                .orElseThrow(() -> new LectureHandler(ErrorStatus.LECTURE_NOT_FOUND));

        Specification<Review> spec = Specification.where((root, query, cb) -> cb.equal(root.get("lecture"), lecture));

        if (ratingMin != null && ratingMax != null) {
            spec = spec.and((root, query, cb) -> cb.between(root.get("rating"), ratingMin, ratingMax));
        } else if (ratingMin != null) {
            spec = spec.and((root, query, cb) -> cb.greaterThanOrEqualTo(root.get("rating"), ratingMin));
        } else if (ratingMax != null) {
            spec = spec.and((root, query, cb) -> cb.lessThanOrEqualTo(root.get("rating"), ratingMax));
        }

        Sort sort = "recommend".equals(sortField)
                ? Sort.by(Sort.Order.desc("likes")).and(Sort.by(Sort.Order.desc("createdAt")))
                : Sort.by(Sort.Order.desc("createdAt"));

        Pageable pageable = PageRequest.of(page, 5, sort);
        Page<Review> reviewPage = reviewRepository.findAll(spec, pageable);

        List<ReviewWithImageResponseDTO> dtoList = reviewPage.getContent().stream().map(review ->
                ReviewWithImageResponseDTO.builder()
                        .reviewId(review.getReviewId())
                        .content(review.getContent())
                        .rating(Double.valueOf(review.getRating()))
                        .period(review.getStudyPeriod().getDescription())
                        .likes(review.getLikes())
                        .imageUrl(review.getReviewImageUrl())
                        .createdAt(review.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
                        .build()
        ).toList();

        // 전체 리뷰 중 조건에 맞는 리뷰 개수 (페이지 크기와 무관)
        Long totalMatchingReviews = reviewRepository.count(spec);

        return ReviewListDTO.builder()
                .reviews(dtoList)
                .listSize(dtoList.size())
                .totalPage(reviewPage.getTotalPages())
                .totalElements(reviewPage.getTotalElements())
                .isFirst(reviewPage.isFirst())
                .isLast(reviewPage.isLast())
                .totalMatchingReviews(totalMatchingReviews)  // DTO에 필드 추가 필요
                .build();
    }
}
