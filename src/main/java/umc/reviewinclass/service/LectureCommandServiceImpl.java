package umc.reviewinclass.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.reviewinclass.converter.LectureConverter;
import umc.reviewinclass.domain.Lecture;
import umc.reviewinclass.repository.LectureRepository;
import umc.reviewinclass.web.dto.LectureRequestDTO;

@Service
@Transactional
@RequiredArgsConstructor
public class LectureCommandServiceImpl extends LectureCommandService {

    private final LectureRepository lectureRepository;

    @Override
    public Lecture createLecture(LectureRequestDTO.createLectureDTO request) {

        Lecture lecture = LectureConverter.createLecture(request);

        return lectureRepository.save(lecture);
    }
}
