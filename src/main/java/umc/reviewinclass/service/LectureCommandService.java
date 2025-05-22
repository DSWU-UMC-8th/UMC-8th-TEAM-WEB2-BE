package umc.reviewinclass.service;

import umc.reviewinclass.domain.Lecture;
import umc.reviewinclass.web.dto.LectureRequestDTO;

public abstract class LectureCommandService {
    public abstract Lecture createLecture(LectureRequestDTO.createLectureDTO request);
}
