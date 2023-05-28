package deeplook.server.global.common.response;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class DataResponse<T> extends BaseResponse {

    private final T data;

    public DataResponse(T data) {
        super(true, "200", "호출에 성공했습니다.");
        this.data = data;
    }

    public DataResponse(T data, String message) {
        super(true, "200", message);
        this.data = data;
    }

    public static <T> DataResponse<T> of(T data) {
        return new DataResponse<>(data);
    }

    public static <T> DataResponse<T> of(T data, String message) {
        return new DataResponse<>(data, message);
    }

    public static <T> DataResponse<T> empty() {
        return new DataResponse<>(null);
    }
}
