package umc.reviewinclass.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StudyPeriod {
    WITHIN_A_WEEK("일주일 이내"),
    WITHIN_A_MONTH("1달 이내"),
    WITHIN_THREE_MONTHS("3달 이내"),
    WITHIN_SIX_MONTHS("6달 이내"),
    WITHIN_A_YEAR("1년 이내"),
    NOT_COMPLETED("수강 미완료");

    private final String description;
}