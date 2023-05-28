package deeplook.server.domain.comment.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentRequestDto {
    private String body;

    public CommentRequestDto(String body) {
        this.body = body;
    }
}
