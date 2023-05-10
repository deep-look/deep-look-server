package deeplook.server.domain.user.entity;

import deeplook.server.global.entity.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String profileUrl;

    @Column
    private String uid;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(String name, String uid ,String profileUrl, Role role) {
        this.name = name;
        this.uid = uid;
        this.profileUrl = profileUrl;
        this.role = role;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}