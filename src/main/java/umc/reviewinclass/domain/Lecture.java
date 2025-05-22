package umc.reviewinclass.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import umc.reviewinclass.domain.common.BaseEntity;
import umc.reviewinclass.domain.enums.Category;
import umc.reviewinclass.domain.enums.Level;
import umc.reviewinclass.domain.enums.Platform;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@DynamicUpdate
@DynamicInsert
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Lecture extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 강의명
    @Column(nullable = false, length = 20)
    private String name;

    // 강사명
    @Column(nullable = false, length = 20)
    private String instructorName;

    // 플랫폼(enum)
    @Enumerated(EnumType.STRING)
    @Column(name = "platform", nullable = false)
    private Platform platform;

    // 난이도(enum)
    @Enumerated(EnumType.STRING)
    @Column(name = "level", nullable = false)
    private Level level;

    // 카테고리(enum)
    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    private Category category;

    // 강의 썸네일
    @JsonIgnore
    @OneToMany(mappedBy = "lecture", cascade = CascadeType.ALL)
    private List<LectureImage> lectureImages = new ArrayList<>();
}
