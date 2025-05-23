package umc.reviewinclass.web.dto.platform;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlatformSearchResponseDTO {
    private List<PlatformDTO> platforms;

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PlatformDTO {
        private Long id;
        private String name;
    }
}