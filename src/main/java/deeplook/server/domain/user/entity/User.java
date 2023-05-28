package deeplook.server.domain.user.entity;

import deeplook.server.global.common.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String profileImage;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Provider provider;
    @Column
    private String oid;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
    @Builder
    public User(String name, String oid, String profileImage, String provider, Role role) {
        this.name = name;
        this.oid = oid;
        this.profileImage = profileImage;
        this.provider = Provider.valueOf(provider);
        this.role = role;
        this.getCreatedDate();
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}