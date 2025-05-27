package umc.reviewinclass.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;
import umc.reviewinclass.domain.review.QReview;
import umc.reviewinclass.web.dto.review.ReviewSearchListResponseDTO;
import org.springframework.data.domain.Pageable;
import umc.reviewinclass.domain.enums.CategoryType;
import umc.reviewinclass.domain.enums.Level;
import umc.reviewinclass.domain.enums.StudyPeriod;
import umc.reviewinclass.domain.review.Review;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    private final QReview review = QReview.review;

    /**
     * 리뷰 검색 조건에 따라 필터링, 정렬된 결과를 페이지 객체로 리턴합니다.
     *
     * @param query
     * @param sort
     * @param minRating
     * @param maxRating
     * @param pageable
     * @return 정렬, 필터링된 page 객체
     */
    @Override
    public Page<ReviewSearchListResponseDTO.ReviewDTO> searchReviews(
            String query, String sort, Double minRating, Double maxRating, Pageable pageable) {

        BooleanBuilder builder = new BooleanBuilder();
        if (query != null && !query.isBlank()) {
            builder.and(review.content.containsIgnoreCase(query));
        }
        // 별점 필터링
        builder.and(review.rating.goe(minRating));
        builder.and(review.rating.loe(maxRating));

        OrderSpecifier<?> orderSpecifier = switch (sort) {
            case "likes" -> review.likes.desc();
            case "latest" -> review.createdAt.desc();
            default -> review.createdAt.desc();
        };

        List<ReviewSearchListResponseDTO.ReviewDTO> results = queryFactory
                .select(Projections.constructor(ReviewSearchListResponseDTO.ReviewDTO.class,
                        review.reviewId,
                        review.lecture.lectureId,
                        review.content,
                        review.rating,
                        review.studyPeriod.stringValue(),
                        review.likes,
                        review.createdAt.stringValue()
                ))
                .from(review)
                .where(builder)
                .orderBy(orderSpecifier)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = queryFactory
                .select(review.count())
                .from(review)
                .where(builder)
                .fetchOne();

        return new PageImpl<>(results, pageable, total);
    }

    @Override
    public List<Review> filterReviews(CategoryType category, Level level, StudyPeriod period) {
        QReview review = QReview.review;

        BooleanBuilder builder = new BooleanBuilder();

        if (category != null) builder.and(review.lecture.category.eq(category));
        if (level != null) builder.and(review.lecture.level.eq(level));
        if (period != null) builder.and(review.studyPeriod.eq(period));

        return queryFactory
                .selectFrom(review)
                .join(review.lecture).fetchJoin()
                .where(builder)
                .orderBy(review.likes.desc())
                .fetch();
    }
}
