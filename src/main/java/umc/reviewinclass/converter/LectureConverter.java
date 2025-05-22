package umc.reviewinclass.converter;

import umc.reviewinclass.domain.Lecture;
import umc.reviewinclass.web.dto.LectureRequestDTO;

public class LectureConverter {
    public static Lecture createLecture(LectureRequestDTO.createLectureDTO request) {
        return Lecture.builder()
                .name(request.getName())
                .instructorName(request.getInstructorName())
                .platform(request.getPlatform())
                .level(request.getLevel())
                .category(request.getCategory())
                .build();
    }
}
