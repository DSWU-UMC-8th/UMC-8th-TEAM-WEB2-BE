package umc.reviewinclass.service.PlatformService;

import umc.reviewinclass.web.dto.platform.PlatformSearchResponseDTO;

import java.util.List;

public interface PlatformQueryService {
    List<PlatformSearchResponseDTO.PlatformDTO> search(String query);
}
