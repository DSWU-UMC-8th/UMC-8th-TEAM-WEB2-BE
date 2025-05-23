package umc.reviewinclass.domain.lecture;

import jakarta.persistence.*;
import lombok.*;
import umc.reviewinclass.domain.common.BaseEntity;
import umc.reviewinclass.domain.enums.CategoryType;
import umc.reviewinclass.domain.enums.Level;
import umc.reviewinclass.domain.platform.Platform;
import umc.reviewinclass.domain.review.Review;

import java.util.ArrayList;
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

    private String instructorName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Level level;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoryType category;

    @OneToMany(mappedBy = "lecture", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "lecture", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LectureImage> lectureImages = new ArrayList<>();

    // 이미 만들어져있는 플랫폼 테이블에 다대일로 연결되어 있다고 생각하고 코드 작성
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "platform_id", nullable = false)
    private Platform platform;
}