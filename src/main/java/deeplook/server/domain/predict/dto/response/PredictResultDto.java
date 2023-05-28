package deeplook.server.domain.predict.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
public class PredictResultDto {
    private String celebrityInitial;
    private String accuracy;

    @Builder
    public PredictResultDto(String celebrityInitial, String accuracy) {
        this.celebrityInitial = celebrityInitial;
        this.accuracy = accuracy;
    }
}
