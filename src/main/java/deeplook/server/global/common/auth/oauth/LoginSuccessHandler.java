package deeplook.server.global.common.auth.oauth;

import deeplook.server.domain.user.entity.User;
import deeplook.server.domain.user.repository.UserRepository;
import deeplook.server.global.common.auth.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;
@Component
@RequiredArgsConstructor
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        OAuth2User oAuth2User1 = (OAuth2User) authentication.getPrincipal();
        String id = String.valueOf(oAuth2User1.getAttributes().get("id"));

        User user = userRepository.findByOid(id).get();

        String tartgetUri;
        tartgetUri = UriComponentsBuilder
                .fromUriString(setRedirectUrl(request.getServerName()))
                .queryParam("userId", user.getId())
                .build().toUriString();
        getRedirectStrategy().sendRedirect(request, response, tartgetUri);
    }
    private String setRedirectUrl(String url) {
        String redirect_url = null;
//        if (url.equals("localhost")) {
//            redirect_url = "http://localhost:3000/oauth/callback/kakao";
//        }
//        if (url.equals("deeplookingproject.com")) {
//            redirect_url = "https://deeplookingproject.com/oauth/callback/kakao";
//        }
        redirect_url = "http://localhost:3000/oauth/callback/kakao";
        return redirect_url;
    }

    // 로그인 실패 후 성공 시 남아있는 에러 세션 제거
    protected void clearSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        }
    }
}