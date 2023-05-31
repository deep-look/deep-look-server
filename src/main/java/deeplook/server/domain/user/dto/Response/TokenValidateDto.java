package deeplook.server.domain.user.dto.Response;

import lombok.Data;

@Data
public class TokenValidateDto {
    private Boolean isValidated;

    public static TokenValidateDto toValidate(Boolean isValidated) {
        return new TokenValidateDto(isValidated);
    }

    public TokenValidateDto(Boolean isValidated) {
        this.isValidated = isValidated;
    }
}
