package umc.reviewinclass.domain.review;

import jakarta.persistence.*;
import lombok.*;
import umc.reviewinclass.domain.common.BaseEntity;
import umc.reviewinclass.domain.lecture.Lecture;
import umc.reviewinclass.domain.enums.StudyPeriod;
import umc.reviewinclass.domain.lecture.LectureImage;
import umc.reviewinclass.domain.mapping.ReviewPlatform;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    private Float rating;

    private String content;

    @Enumerated(EnumType.STRING)
    private StudyPeriod studyPeriod;

    private Long likes = 0L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;

    @Column(name = "review_image_url", columnDefinition = "TEXT")
    private String reviewImageUrl;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL)
    private List<ReviewPlatform> reviewPlatforms;

    public void increaseLikes() {
        this.likes++;
    }

}
