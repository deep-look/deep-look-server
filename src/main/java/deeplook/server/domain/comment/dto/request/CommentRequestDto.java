package deeplook.server.domain.comment.dto.request;

import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentRequestDto {
    @NotNull
    @ApiModelProperty(value = "댓글 내용", example = "hello~~", required = true)
    private String body;

    public CommentRequestDto(String body) {
        this.body = body;
    }
}
