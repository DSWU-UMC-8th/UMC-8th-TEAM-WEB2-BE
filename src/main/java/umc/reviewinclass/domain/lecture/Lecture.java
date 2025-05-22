package umc.reviewinclass.domain.lecture;

import jakarta.persistence.*;
import lombok.*;
import umc.reviewinclass.domain.common.BaseEntity;
import umc.reviewinclass.domain.enums.CategoryType;
import umc.reviewinclass.domain.review.Review;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lecture extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lectureId;

    private String name;

    private String platform; // 외래키 아님

    private String instructorName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Level level;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoryType category;

    @OneToMany(mappedBy = "lecture", cascade = CascadeType.ALL)
    private List<Review> reviews;
}