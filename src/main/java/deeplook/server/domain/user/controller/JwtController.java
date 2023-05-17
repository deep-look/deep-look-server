package deeplook.server.domain.user.controller;

import deeplook.server.domain.user.dto.Request.JwtRequestDta;
import deeplook.server.domain.user.dto.Response.JwtResponseDto;
import deeplook.server.domain.user.service.JwtService;
import deeplook.server.global.common.auth.Login;
import deeplook.server.global.common.auth.oauth.dto.LoginUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/token")
public class JwtController {
    private final JwtService jwtService;

//
//    /* access token 재발행 */
//    @PostMapping("/reIssuance")
//    public JwtResponseDto reIssuance(@Login LoginUser user, @RequestBody JwtRequestDta postJwtTokenDto) {
//        return jwtService.reIssuance(user, postJwtTokenDto);
//    }

}
