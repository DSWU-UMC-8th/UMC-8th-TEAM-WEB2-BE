package umc.reviewinclass.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.reviewinclass.converter.LectureConverter;
import umc.reviewinclass.domain.Lecture;
import umc.reviewinclass.domain.LectureImage;
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

        // 이미지 추가
        if(request.getImgUrls() != null){
            for (String imageUrl : request.getImgUrls()) {
                LectureImage lectureImage = LectureImage.builder()
                        .image_url(imageUrl)
                        .lecture(lecture)  // 연관관계 설정
                        .build();

                lecture.getLectureImages().add(lectureImage);  // 양방향 연관관계 설정
            }
        }

        return lectureRepository.save(lecture);
    }
}
