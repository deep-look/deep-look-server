package deeplook.server.global.config;

import deeplook.server.global.common.auth.jwt.*;
import deeplook.server.global.common.auth.oauth.CustomOAuth2UserService;
import deeplook.server.global.common.auth.oauth.LoginSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig{

    private final CustomOAuth2UserService customOAuth2UserService;
    private final CustomEntryPoint customEntryPoint;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;
    private final JwtExceptionFilter jwtExceptionFilter;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final LoginSuccessHandler loginSuccessHandler;

    private static final String[] SwaggerUrlPatterns = {
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            /* swagger 3 */
            "/v3/api-docs/**",
            "/swagger-ui/**"
    };

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .httpBasic().disable()
                .cors().configurationSource(corsConfigurationSource()) // cors 적용
                .and()
                .csrf().disable()
                .sessionManagement( ).sessionCreationPolicy(SessionCreationPolicy.STATELESS) // JWT이용으로 세션 이용 x
                .and()
                .formLogin().disable()
                .httpBasic().disable()
                .authorizeRequests()
                .antMatchers(SwaggerUrlPatterns).permitAll()
                .antMatchers("/login/**").permitAll()
                .antMatchers("/oauth2/**").permitAll()
                .antMatchers("/token/**").permitAll()
//                .antMatchers("/v1/**").hasRole(Role.USER.name())
                .anyRequest().authenticated();
        /* jwt 인증 실패시 */
        http
                .exceptionHandling().authenticationEntryPoint(customEntryPoint).accessDeniedHandler(customAccessDeniedHandler);

        /* 로그인 로직 */
        http
                .oauth2Login().userInfoEndpoint().userService(customOAuth2UserService)
                .and()
                .successHandler(loginSuccessHandler);


        http
                .headers().frameOptions().disable();
        http
                .logout()
                .logoutSuccessUrl("/"); //이 주소로 redirect
        /* jwt 로직 */
        http
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtExceptionFilter, JwtAuthenticationFilter.class);
        return http.build();
    }


    @Bean       /* 임시적으로 cors 모두 허용 */
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.addAllowedOrigin("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}