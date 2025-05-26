package umc.reviewinclass.service.LectureService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import umc.reviewinclass.apiPayload.code.status.ErrorStatus;
import umc.reviewinclass.apiPayload.exception.handler.PlatformHandler;
import umc.reviewinclass.aws.s3.AmazonS3Manager;
import umc.reviewinclass.converter.LectureConverter;
import umc.reviewinclass.domain.common.Uuid;
import umc.reviewinclass.domain.lecture.Lecture;
import umc.reviewinclass.domain.lecture.LectureImage;
import umc.reviewinclass.domain.platform.Platform;
import umc.reviewinclass.repository.LectureRepository;
import umc.reviewinclass.repository.PlatformRepository;
import umc.reviewinclass.repository.UuidRepository;
import umc.reviewinclass.web.dto.lecture.LectureRequestDTO;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class LectureCommandServiceImpl extends LectureCommandService {

    private final LectureRepository lectureRepository;
    private final PlatformRepository platformRepository;
    private final AmazonS3Manager amazonS3Manager;
    private final UuidRepository uuidRepository;

    @Override
    public Lecture createLecture(LectureRequestDTO.createLectureDTO request, List<MultipartFile> images) {

        Platform platform = platformRepository.findById(request.getPlatformId())
                .orElseThrow(() -> new PlatformHandler(ErrorStatus.PLATFORM_NOT_FOUND));

        Lecture lecture = LectureConverter.createLecture(request, platform);

        // 이미지 업로드 후 LectureImage 저장
        if (images != null && !images.isEmpty()) {
            for (MultipartFile file : images) {
                if (!file.isEmpty()) {
                    Uuid uuidEntity = uuidRepository.save(Uuid.builder()
                            .uuid(UUID.randomUUID().toString())
                            .build());

                    String keyName = amazonS3Manager.generateLectureImageKeyName(uuidEntity);
                    String imageUrl = amazonS3Manager.uploadFile(keyName, file);

                    LectureImage lectureImage = LectureImage.builder()
                            .lectureImageUrl(imageUrl)
                            .lecture(lecture)
                            .build();

                    lecture.getLectureImages().add(lectureImage);
                }
            }
        }

        return lectureRepository.save(lecture);
    }
}
