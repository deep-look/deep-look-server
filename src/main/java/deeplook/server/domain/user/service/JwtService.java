package deeplook.server.domain.user.service;

import deeplook.server.domain.user.entity.User;
import deeplook.server.domain.user.repository.RefreshTokenRepository;
import deeplook.server.domain.user.repository.UserRepository;
import deeplook.server.global.common.auth.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class JwtService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;


    public User getUser(String token){
        Long userId = jwtTokenProvider.findUserIdByJwt(token);
        User user = userRepository.findById(userId).get();
        return user;
    }

}