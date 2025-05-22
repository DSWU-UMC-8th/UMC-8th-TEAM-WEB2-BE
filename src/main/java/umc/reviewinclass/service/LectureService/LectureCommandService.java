package umc.reviewinclass.service.LectureService;

import umc.reviewinclass.domain.lecture.Lecture;
import umc.reviewinclass.web.dto.LectureRequestDTO;

public abstract class LectureCommandService {
    public abstract Lecture createLecture(LectureRequestDTO.createLectureDTO request);
}
