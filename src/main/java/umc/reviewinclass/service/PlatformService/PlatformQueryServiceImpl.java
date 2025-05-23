package umc.reviewinclass.service.PlatformService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.reviewinclass.repository.PlatformRepository;
import umc.reviewinclass.web.dto.platform.PlatformSearchResponseDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlatformQueryServiceImpl implements PlatformQueryService {
    private final PlatformRepository platformRepository;

    /**
     * 플랫폼의 이름을 검색합니다.
     *
     * @param query
     * @return platformDTO list
     */
    @Override
    public List<PlatformSearchResponseDTO.PlatformDTO> search(String query) {
        List<PlatformSearchResponseDTO.PlatformDTO> platforms = platformRepository.findByNameContaining(query).stream()
                .map(p -> PlatformSearchResponseDTO.PlatformDTO.builder()
                        .id(p.getPlatformId())
                        .name(p.getName())
                        .build())
                .toList();

        return platforms;
    }
}