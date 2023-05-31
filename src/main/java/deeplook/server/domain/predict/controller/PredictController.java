package deeplook.server.domain.predict.controller;

import deeplook.server.domain.predict.dto.request.ImageRequestDto;
import deeplook.server.domain.predict.dto.response.PredictResultDto;
import deeplook.server.domain.predict.service.PredictService;
import deeplook.server.global.common.auth.Login;
import deeplook.server.global.common.auth.oauth.dto.LoginUser;
import deeplook.server.global.common.response.DataResponse;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/predict")
@Api(tags = {"유사도 측정 API"})
public class PredictController {

    private final PredictService predictService;

    @PostMapping("")
    @Operation(summary = "유사도 측정 진행 API", description = "클라이언트에게 받은 이미지로 유사도 측정을 진행합니다. 측정결과를 반환합니다.")
    public DataResponse<PredictResultDto> predictExcute(@Login LoginUser user,
                                                        @Validated @ModelAttribute ImageRequestDto image){
        log.info("유사도 측정 진행 API 호출 USER = {}",user.getId());
        return new DataResponse<PredictResultDto>(predictService.predictExcute(user,image));
    }
    @GetMapping("")
    @Operation(summary = "측정 기록 전체 조회 API", description = "본인의 측정 기록을 조회합니다.")
    public DataResponse<List<PredictResultDto>> getPredictResult(@Login LoginUser user){
        log.info("측정 기록 전체 조회 API 호출 USER = {}",user.getId());
        return new DataResponse<List<PredictResultDto>>(predictService.getPredictResult(user));
    }
}
