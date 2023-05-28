package deeplook.server.global.feign;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeignPredictDto {
    private String celebrity_initial;
    private String accuracy;

}
