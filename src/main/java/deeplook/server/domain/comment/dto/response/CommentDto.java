package deeplook.server.domain.comment.dto.response;

import deeplook.server.domain.comment.entity.Comment;
import deeplook.server.domain.user.entity.User;
import deeplook.server.global.common.auth.oauth.dto.LoginUser;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

@Data
public class CommentDto {
    private Long commentId;
    private Long authorId;
    private String authorName;
    private String authorProfileImage;
    private LocalDateTime createdTime;
    private String body;
    private Boolean isAuthor;
    private Boolean isDeleted;

    @Builder
    public CommentDto(LoginUser user, Comment comment) {
        this.commentId = comment.getId();
        this.authorId = comment.getAuthor().getId();
        this.authorName = comment.getAuthor().getName();
        this.authorProfileImage = comment.getAuthor().getProfileImage();
        this.createdTime = comment.getCreatedDate();
        this.body = comment.getBody();
        this.isDeleted = comment.getIsDeleted();
        if (user.getId().equals(comment.getAuthor().getId()))
            this.isAuthor = true;
        else this.isAuthor = false;
    }
}
