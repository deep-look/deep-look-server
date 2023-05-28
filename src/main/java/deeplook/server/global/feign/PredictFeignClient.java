package deeplook.server.global.feign;

import deeplook.server.domain.predict.dto.request.ImageRequestDto;
import deeplook.server.domain.predict.exception.PredictServerIntervalServerException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "feign-predict-api", url = "${feign.post-api.url}")
public interface PredictFeignClient {
    @PostMapping(value = "/predict",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    FeignPredictDto predictImage(@ModelAttribute ImageRequestDto image) throws PredictServerIntervalServerException;
}
