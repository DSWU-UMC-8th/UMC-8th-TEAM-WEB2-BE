package umc.reviewinclass.converter;

import umc.reviewinclass.domain.lecture.Lecture;
import umc.reviewinclass.domain.platform.Platform;
import umc.reviewinclass.web.dto.lecture_tmp.LectureRequestDTO;

import java.util.ArrayList;

public class LectureConverter {
    public static Lecture createLecture(LectureRequestDTO.createLectureDTO request, Platform platform) {
        return Lecture.builder()
                .name(request.getName())
                .instructorName(request.getInstructorName())
                .platform(platform)
                .level(request.getLevel())
                .category(request.getCategory())
                .lectureImages(new ArrayList<>())
                .build();
    }
}
