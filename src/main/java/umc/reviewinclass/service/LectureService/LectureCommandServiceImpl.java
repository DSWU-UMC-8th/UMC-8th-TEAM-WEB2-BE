package umc.reviewinclass.service.LectureService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.reviewinclass.apiPayload.code.status.ErrorStatus;
import umc.reviewinclass.apiPayload.exception.handler.PlatformHandler;
import umc.reviewinclass.converter.LectureConverter;
import umc.reviewinclass.domain.lecture.Lecture;
import umc.reviewinclass.domain.lecture.LectureImage;
import umc.reviewinclass.domain.platform.Platform;
import umc.reviewinclass.repository.LectureRepository;
import umc.reviewinclass.repository.PlatformRepository;
import umc.reviewinclass.web.dto.lecture_tmp.LectureRequestDTO;

@Service
@Transactional
@RequiredArgsConstructor
public class LectureCommandServiceImpl extends LectureCommandService {

    private final LectureRepository lectureRepository;
    private final PlatformRepository platformRepository;

    @Override
    public Lecture createLecture(LectureRequestDTO.createLectureDTO request) {

        Platform platform = platformRepository.findById(request.getPlatformId())
                .orElseThrow(() -> new PlatformHandler(ErrorStatus.PLATFORM_NOT_FOUND));

        Lecture lecture = LectureConverter.createLecture(request, platform);

        // 이미지 추가
        if(request.getImgUrls() != null){
            for (String imageUrl : request.getImgUrls()) {
                LectureImage lectureImage = LectureImage.builder()
                        .lectureImageUrl(imageUrl)
                        .lecture(lecture)  // 연관관계 설정
                        .build();

                lecture.getLectureImages().add(lectureImage);  // 양방향 연관관계 설정
            }
        }

        return lectureRepository.save(lecture);
    }
}
