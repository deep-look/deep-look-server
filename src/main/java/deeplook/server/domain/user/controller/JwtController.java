package deeplook.server.domain.user.controller;

import deeplook.server.domain.user.dto.Request.UserIdDto;
import deeplook.server.domain.user.dto.Response.JwtResponseDto;
import deeplook.server.domain.user.service.JwtService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/token")
@Api(tags = {"JWT토큰 API"})
public class JwtController {
    private final JwtService jwtService;

    @PostMapping("/issue")
    @Operation(summary = "JWT 생성 API", description = "UserId로 JWT토큰을 생성합니다.")
    public JwtResponseDto getTokenByOid(@RequestBody UserIdDto userIdDto) throws IOException {
        Long userId = userIdDto.getUserId();
        JwtResponseDto jwtResponseDto = jwtService.login(userId);
        return jwtResponseDto;
    }
//
//
//    /* access token 재발행 */
//    @PostMapping("/reIssuance")
//    public JwtResponseDto reIssuance(@Login LoginUser user, @RequestBody JwtRequestDta postJwtTokenDto) {
//        return jwtService.reIssuance(user, postJwtTokenDto);
//    }

}
