package deeplook.server.domain.user.controller;

import deeplook.server.domain.user.dto.Request.JwtRequestDta;
import deeplook.server.domain.user.dto.Response.JwtResponseDto;
import deeplook.server.domain.user.service.JwtService;
import deeplook.server.global.common.auth.Login;
import deeplook.server.global.common.auth.oauth.dto.LoginUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/token")
public class JwtController {
    private final JwtService jwtService;

    @PostMapping("/issue")
    public JwtResponseDto getTokenByOid(@RequestBody Map<String,Long> userId) throws IOException {
        Long uid = userId.get("userId");
        JwtResponseDto jwtResponseDto = jwtService.login(uid);
        return jwtResponseDto;
    }

    @GetMapping("/test")
    public Long getTokenByOid(@Login LoginUser user){

        return user.getId();
    }
//
//
//    /* access token 재발행 */
//    @PostMapping("/reIssuance")
//    public JwtResponseDto reIssuance(@Login LoginUser user, @RequestBody JwtRequestDta postJwtTokenDto) {
//        return jwtService.reIssuance(user, postJwtTokenDto);
//    }

}
