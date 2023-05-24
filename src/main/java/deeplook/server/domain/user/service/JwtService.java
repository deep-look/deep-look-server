package deeplook.server.domain.user.service;

import deeplook.server.domain.user.dto.Response.JwtResponseDto;
import deeplook.server.domain.user.entity.RefreshToken;
import deeplook.server.domain.user.entity.Role;
import deeplook.server.domain.user.entity.User;
import deeplook.server.domain.user.repository.RefreshTokenRepository;
import deeplook.server.domain.user.repository.UserRepository;
import deeplook.server.global.common.auth.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class JwtService {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtTokenProvider jwtTokenProvider;
    @Transactional
    public JwtResponseDto login(Long uid){
        // 해당 유저 찾기
        User user = userRepository.findById(uid).get();
        Role role = user.getRole();
        // JWT 만들기
        String refreshToken = jwtTokenProvider.generateRefreshToken(uid, role.toString());
        String accessToken = jwtTokenProvider.generateAccessToken(uid, role.toString());

        Optional<RefreshToken> findedrefreshToken = refreshTokenRepository.findByUserId(uid);
        if (findedrefreshToken.isEmpty()) saveRefreshToken(uid,refreshToken);
        else {
            RefreshToken oldRefreshToken = findedrefreshToken.get();
            oldRefreshToken.update(refreshToken);
            refreshTokenRepository.save(oldRefreshToken);
        }

        return JwtResponseDto.builder()
                .refreshToken(refreshToken)
                .accessToken(accessToken)
                .build();
    }

    public User getUser(String token){
        Long userId = jwtTokenProvider.findUserIdByJwt(token);
        User user = userRepository.findById(userId).get();
        return user;
    }
    @Transactional
    public void saveRefreshToken(Long uid, String refreshToken){
        RefreshToken rt = RefreshToken.builder()
                .userId(uid)
                .refreshToken(refreshToken)
                .build();
        refreshTokenRepository.save(rt);
    }

}