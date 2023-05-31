package deeplook.server.domain.comment.controller;

import deeplook.server.domain.comment.dto.request.CommentRequestDto;
import deeplook.server.domain.comment.dto.response.CommentDto;
import deeplook.server.domain.comment.service.CommentService;
import deeplook.server.domain.predict.dto.request.ImageRequestDto;
import deeplook.server.domain.predict.dto.response.PredictResultDto;
import deeplook.server.global.common.auth.Login;
import deeplook.server.global.common.auth.oauth.dto.LoginUser;
import deeplook.server.global.common.response.BaseResponse;
import deeplook.server.global.common.response.DataResponse;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/comment")
@Api(tags = {"댓글 API"})
public class CommentController {
    private final CommentService commentService;

    @PostMapping("")
    @Operation(summary = "댓글 생성 API", description = "댓글을 생성합니다.")
    public BaseResponse createComment(@Login LoginUser user,
                                                        @Validated @RequestBody CommentRequestDto commentRequestDto){
        log.info("댓글 생성 API 호출 USER = {}", user.getId());
        commentService.createComment(user, commentRequestDto);
        return BaseResponse.success();
    }
    @GetMapping("")
    @Operation(summary = "댓글 전체 조회 API", description = "댓글 전체를 조회합니다.")
    public BaseResponse getComments(@Login LoginUser user){
        log.info("댓글 전체 조회 API 호출 USER = {}", user.getId());
        return new DataResponse<List<CommentDto>>(commentService.getComments(user));
    }

}
