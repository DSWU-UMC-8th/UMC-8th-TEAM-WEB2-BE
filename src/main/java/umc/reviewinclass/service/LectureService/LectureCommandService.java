package umc.reviewinclass.service.LectureService;

import org.springframework.web.multipart.MultipartFile;
import umc.reviewinclass.domain.lecture.Lecture;
import umc.reviewinclass.web.dto.lecture.LectureRequestDTO;

import java.util.List;

public abstract class LectureCommandService {
    public abstract Lecture createLecture(LectureRequestDTO.createLectureDTO request, List<MultipartFile> images);
}
