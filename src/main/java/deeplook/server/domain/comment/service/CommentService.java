package deeplook.server.domain.comment.service;

import deeplook.server.domain.comment.dto.request.CommentRequestDto;
import deeplook.server.domain.comment.dto.response.CommentDto;
import deeplook.server.domain.comment.entity.Comment;
import deeplook.server.domain.comment.exception.CommentNotFoundException;
import deeplook.server.domain.comment.repository.CommentRepository;
import deeplook.server.domain.user.entity.User;
import deeplook.server.domain.user.repository.UserRepository;
import deeplook.server.global.common.auth.oauth.dto.LoginUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @Transactional
    public void createComment(LoginUser loginUser, CommentRequestDto commentRequestDto){
        User user = userRepository.findById(loginUser.getId()).get();

        Comment comment = Comment.builder()
                .author(user)
                .body(commentRequestDto.getBody())
                .isDeleted(false)
                .build();
        commentRepository.save(comment);
    }

    public List<CommentDto> getComments(LoginUser loginUser){
        List<Comment> comments = Optional.of(commentRepository.findAll())
                                    .orElseThrow(()-> new CommentNotFoundException());

        return comments.stream()
                .map(comment -> CommentDto.builder()
                        .user(loginUser)
                        .comment(comment)
                        .build())
                .collect(Collectors.toList());
    }
}
