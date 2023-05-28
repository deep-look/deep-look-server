package deeplook.server.global.common.auth.jwt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {

    private final JwtTokenProvider jwtTokenProvider;

    /** AccessToken을 검증하고, 해당 유저를 찾아 권한처리를 위해 Context에 넣기 */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String token = null;
        if(httpRequest.getHeader("JWT") != null){
            token = httpRequest.getHeader("JWT");
        }

        if( token != null && jwtTokenProvider.validateToken(token) ) {
            Authentication authentication = jwtTokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication); //SecurityContextHolder에 담기
        }
        /*
           만약 SecurityContextHolder에 담기지않고  chain.doFilter이 돌면
           chain.doFilter를 통해서 다음 필터로 넘어간다면 CustomEntryPoint로.
         */
        chain.doFilter(httpRequest, httpResponse);
    }
}