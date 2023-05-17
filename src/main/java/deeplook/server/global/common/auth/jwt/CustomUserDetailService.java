package deeplook.server.global.common.auth.jwt;

import deeplook.server.domain.user.entity.User;
import deeplook.server.domain.user.repository.UserRepository;
import deeplook.server.global.common.auth.jwt.exception.IncorrectDeleteUserRequestException;
import deeplook.server.global.common.auth.oauth.dto.LoginUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    @Transactional
    public LoginUser loadUserByUsername(String id) {

        User findUser = userRepository.findById(Long.parseLong(id))
                .orElseThrow(() -> new IncorrectDeleteUserRequestException("토큰속 유저가 존재하지않습니다.",403));

        if(findUser != null){
            return new LoginUser(findUser);
        }

        return null;
    }
}