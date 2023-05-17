package deeplook.server.global.common.auth.jwt.exception;

public class IncorrectDeleteUserRequestException extends io.jsonwebtoken.JwtException {

    private final int code;

    public IncorrectDeleteUserRequestException(String message, int code) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}