package deeplook.server.domain.predict.entity;

import deeplook.server.domain.user.entity.User;
import deeplook.server.global.common.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class PredictResult extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id",nullable = false)
    private User author;

    @Column(nullable = false)
    private String celebrityInitial;

    @Column(nullable = false)
    private String accuracy;
    @Builder
    public PredictResult(User author, String celebrityInitial, String accuracy) {
        this.author = author;
        this.celebrityInitial = celebrityInitial;
        this.accuracy = accuracy;
    }
}
