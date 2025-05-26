package umc.reviewinclass.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.reviewinclass.apiPayload.ApiResponse;
import umc.reviewinclass.service.PlatformService.PlatformQueryService;

import java.util.List;

import static umc.reviewinclass.web.dto.platform.PlatformSearchResponseDTO.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/platforms")
@Tag(name = "플랫폼 API", description = "플랫폼 관련 API 입니다.")
public class PlatformController {
    private final PlatformQueryService platformQueryService;

    // 플랫폼 검색
    @GetMapping("/search")
    @Operation(summary = "플랫폼 검색", description = "플랫폼 검색 API 입니다.")
    public ResponseEntity<?> searchPlatforms(@RequestParam String query) {
        List<PlatformDTO> results = platformQueryService.search(query);
        return ResponseEntity.ok(ApiResponse.onSuccess(results));
    }
}