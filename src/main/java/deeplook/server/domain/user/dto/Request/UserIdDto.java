package deeplook.server.domain.user.dto.Request;

import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserIdDto {
    @NotNull
    @ApiModelProperty(value = "유저의 id값", example = "1", required = true)
    private Long userId;

    public UserIdDto(Long userId) {
        this.userId = userId;
    }
}
