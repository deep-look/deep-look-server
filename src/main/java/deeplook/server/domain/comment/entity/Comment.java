package deeplook.server.domain.comment.entity;

import deeplook.server.domain.user.entity.User;
import deeplook.server.global.common.entity.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Comment extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id",nullable = false)
    private User author;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String body;
    private Boolean isDeleted; //default 는 false로 설정하기

    public Comment(String body) {
        this.body = body;
        this.isDeleted = false;
    }

    public Comment(User author, String body) {
        this.author = author;
        this.body = body;
        this.isDeleted = false;
    }

    public void update(String body){
        this.body = body;
    }

    public void delete(){
        this.isDeleted = true;
    }
}

