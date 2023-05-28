package deeplook.server.domain.predict.service;

import deeplook.server.domain.predict.dto.request.ImageRequestDto;
import deeplook.server.domain.predict.dto.response.PredictResultDto;
import deeplook.server.domain.predict.entity.PredictResult;
import deeplook.server.domain.predict.exception.PredictNotFoundException;
import deeplook.server.domain.predict.repository.PredictResultRepository;
import deeplook.server.domain.user.entity.User;
import deeplook.server.domain.user.repository.UserRepository;
import deeplook.server.global.common.auth.oauth.dto.LoginUser;
import deeplook.server.global.feign.FeignPredictDto;
import deeplook.server.global.feign.PredictFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PredictService {
    private final PredictResultRepository predictResultRepository;
    private final UserRepository userRepository;
    private final PredictFeignClient predictFeignClient;

    @Transactional
    public PredictResultDto predictExcute(LoginUser loginUser, ImageRequestDto image){
        FeignPredictDto feignResult = predictFeignClient.predictImage(image);

        User user = userRepository.findById(loginUser.getId()).get();

        PredictResult predictResult = PredictResult.builder()
                .author(user)
                .celebrityInitial(feignResult.getCelebrity_initial())
                .accuracy(feignResult.getAccuracy())
                .build();

        predictResultRepository.save(predictResult);
        return PredictResultDto.builder()
                .celebrityInitial(predictResult.getCelebrityInitial())
                .accuracy(predictResult.getAccuracy())
                .build();

    }
    public List<PredictResultDto> getPredictResult(LoginUser loginUser){
        List<PredictResult> predictResults = Optional.of(predictResultRepository.findByAuthor_Id(loginUser.getId()))
                                                .orElseThrow(() -> new PredictNotFoundException());
        return predictResults.stream()
                .map(result -> PredictResultDto.builder()
                        .celebrityInitial(result.getCelebrityInitial())
                        .accuracy(result.getAccuracy())
                        .build())
                .collect(Collectors.toList());

    }

}
