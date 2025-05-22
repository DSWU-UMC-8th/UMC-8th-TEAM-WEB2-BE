package umc.reviewinclass.domain.lecture;

import jakarta.persistence.*;
import lombok.*;
import umc.reviewinclass.domain.common.BaseEntity;
import umc.reviewinclass.domain.review.Review;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LectureImage extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imgId;

    @ManyToOne
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;

    @Column(name = "lecture_image_url", columnDefinition = "TEXT")
    private String lectureImageUrl;
}
