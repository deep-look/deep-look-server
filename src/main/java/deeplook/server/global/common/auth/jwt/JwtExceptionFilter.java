package deeplook.server.global.common.auth.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import deeplook.server.global.common.auth.jwt.exception.IncorrectDeleteUserRequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtExceptionFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");

        try {
            filterChain.doFilter(request, response); // go to 'JwtAuthenticationFilter'
        } catch (IncorrectDeleteUserRequestException e) {
            Map<String, String> map = new HashMap<>();

            map.put("code", "403");
            map.put("message", "잘못된 토큰입니다.");

            log.error("잘못된 토큰으로 접근");
            response.getWriter().write(objectMapper.writeValueAsString(map));
        }
    }
}
