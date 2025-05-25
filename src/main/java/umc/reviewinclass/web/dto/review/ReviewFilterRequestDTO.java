package umc.reviewinclass.web.dto.review;

import umc.reviewinclass.domain.enums.CategoryType;
import umc.reviewinclass.domain.enums.Level;
import umc.reviewinclass.domain.enums.StudyPeriod;

public class ReviewFilterRequestDTO {
    private CategoryType category;
    private Level level;
    private StudyPeriod period;
}
