package umc.reviewinclass.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Level {
    BEGINNER("입문자"),
    ELEMENTARY("초급자"),
    INTERMEDIATE("중급자"),
    ADVANCED("상급자");

    private final String description;
}