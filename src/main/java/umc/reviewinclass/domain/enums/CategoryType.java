package umc.reviewinclass.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CategoryType {
    IT("IT/프로그래밍"),
    LANGUAGE("언어"),
    DESIGN("디자인"),
    COOKING("요리"),
    FINANCE("금융/재테크"),
    LIFESTYLE("라이프스타일");

    private final String description;
}
