package umc.reviewinclass.aws.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import umc.reviewinclass.config.AmazonConfig;
import umc.reviewinclass.domain.common.Uuid;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AmazonS3Manager {

    private final AmazonS3 amazonS3;
    private final String bucket = "reviewinclass-bucket";
    private final AmazonConfig amazonConfig;

    public String uploadFile(String keyName, MultipartFile file) {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());

        try {
            amazonS3.putObject(bucket, keyName, file.getInputStream(), metadata);
        } catch (IOException e) {
            throw new RuntimeException("S3 파일 업로드 실패", e);
        }

        return amazonS3.getUrl(bucket, keyName).toString(); // 업로드된 파일의 URL
    }

    public String generateLectureImageKeyName(Uuid uuid) {
        return amazonConfig.getLectureImagePath() + "/" + uuid.getUuid();
    }
}