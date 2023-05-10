package deeplook.server.global.config.auth.oauth;



import deeplook.server.domain.user.entity.User;
import deeplook.server.domain.user.repository.UserRepository;
import deeplook.server.global.config.auth.oauth.dto.OAuthAttributes;
import deeplook.server.global.config.auth.oauth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        /*
             OAuth2UserService는 loadUser만 선언된 인터페이스고 DefaultOAuthUserService는 구현체
             -> 이를 이용해서 userRequest 안의 있는 정보를 뻬 낼 수 있다.
         */
        OAuth2User oAuth2User = super.loadUser(userRequest);

        // 소셜 로그인 타입
        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        /*
            userNameAttributeName 는 소셜로그인을 구분하는키입니다.
            로그인을 위한 키 -> google- sub / kakao - id / naver - response \
            소셜로그인 여러개 사용하지 않는 다면 사용하지않습니다.
        */
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
               .getUserInfoEndpoint().getUserNameAttributeName();

        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        /*

         */
        User user = saveOrUpdate(attributes);
        httpSession.setAttribute("user", new SessionUser(user));

        DefaultOAuth2User defaultOAuth2User = new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
        return defaultOAuth2User;
    }

    private User saveOrUpdate(OAuthAttributes attributes) {
        User user = userRepository.findByUid(attributes.getUid())
                .orElse(attributes.toEntity());

        return userRepository.save(user);
    }

}