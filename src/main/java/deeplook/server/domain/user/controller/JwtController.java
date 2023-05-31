package deeplook.server.domain.user.controller;

import deeplook.server.domain.user.dto.Request.UserIdDto;
import deeplook.server.domain.user.dto.Response.JwtResponseDto;
import deeplook.server.domain.user.dto.Response.TokenValidateDto;
import deeplook.server.domain.user.service.JwtService;
import deeplook.server.global.common.auth.Login;
import deeplook.server.global.common.auth.jwt.JwtTokenProvider;
import deeplook.server.global.common.auth.oauth.dto.LoginUser;
import deeplook.server.global.common.response.DataResponse;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.http.HttpHeaders;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/token")
@Api(tags = {"JWT토큰 API"})
public class JwtController {
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtService jwtService;

    @PostMapping("/issue")
    @Operation(summary = "JWT 생성 API", description = "UserId로 JWT토큰을 생성합니다.")
    public JwtResponseDto getTokenByOid(@RequestBody UserIdDto userIdDto) throws IOException {
        Long userId = userIdDto.getUserId();
        JwtResponseDto jwtResponseDto = jwtService.login(userId);
        return jwtResponseDto;
    }
    @GetMapping("/validate")
    @Operation(summary = "JWT 만료 확인", description = "토큰이 만료되었는지 boolean 값으로 표시합니다.")
    public DataResponse<Object> validate(@Validated @RequestHeader("JWT") String token){
        return DataResponse.of(TokenValidateDto.toValidate(jwtTokenProvider.validateToken(token)));
    }
//
//
//    /* access token 재발행 */
//    @PostMapping("/reIssuance")
//    public JwtResponseDto reIssuance(@Login LoginUser user, @RequestBody JwtRequestDta postJwtTokenDto) {
//        return jwtService.reIssuance(user, postJwtTokenDto);
//    }

}
