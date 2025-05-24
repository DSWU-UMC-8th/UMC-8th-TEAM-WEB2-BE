package umc.reviewinclass.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import umc.reviewinclass.domain.enums.CategoryType;
import umc.reviewinclass.domain.enums.Level;
import umc.reviewinclass.domain.enums.StudyPeriod;
import umc.reviewinclass.domain.review.QReview;
import umc.reviewinclass.domain.review.Review;

import java.util.List;

@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {

    private final JPAQueryFactory queryFactory;

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