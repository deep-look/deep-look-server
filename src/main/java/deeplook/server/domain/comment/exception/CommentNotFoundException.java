package deeplook.server.domain.comment.exception;

import deeplook.server.global.exception.BaseErrorCode;
import deeplook.server.global.exception.BaseException;

public class CommentNotFoundException extends BaseException {
    public CommentNotFoundException() {
        super(CommentErrorCode.COMMENT_NOT_FOUND);
    }
}
