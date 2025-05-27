package umc.reviewinclass.converter;

import umc.reviewinclass.domain.review.Review;
import umc.reviewinclass.web.dto.review.LatestReviewResponseDTO;
import umc.reviewinclass.web.dto.review.PopularReviewResponseDTO;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static PopularReviewResponseDTO toPopularDTO(Review review) {
        return PopularReviewResponseDTO.builder()
                .reviewId(review.getReviewId())
                .lectureId(review.getLecture().getLectureId())
                .lectureName(review.getLecture().getName())
                .instructorName(review.getLecture().getInstructorName())
                .rating(review.getRating().doubleValue())
                .content(review.getContent())
                .likes(review.getLikes())
                .build();
    }

    public static LatestReviewResponseDTO toLatestDTO(Review review) {
        return LatestReviewResponseDTO.builder()
                .reviewId(review.getReviewId())
                .lectureId(review.getLecture().getLectureId())
                .rating(review.getRating().doubleValue())
                .studyPeriod(review.getStudyPeriod().getDescription())
                .likes(review.getLikes())
                .content(review.getContent())
                .createdAt(review.getCreatedAt().format(formatter))
                .build();
    }

    public static List<PopularReviewResponseDTO> toPopularDTOList(List<Review> reviews) {
        return reviews.stream().map(ReviewConverter::toPopularDTO).collect(Collectors.toList());
    }

    public static List<LatestReviewResponseDTO> toLatestDTOList(List<Review> reviews) {
        return reviews.stream().map(ReviewConverter::toLatestDTO).collect(Collectors.toList());
    }

}
