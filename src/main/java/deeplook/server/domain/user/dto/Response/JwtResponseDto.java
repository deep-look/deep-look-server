package deeplook.server.domain.user.dto.Response;

import lombok.Builder;
import lombok.Data;

@Data
public class JwtResponseDto {
    private String accessToken;
    private String refreshToken;
    @Builder
    public JwtResponseDto(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
