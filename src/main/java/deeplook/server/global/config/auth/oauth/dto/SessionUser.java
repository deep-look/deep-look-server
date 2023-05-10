package deeplook.server.global.config.auth.oauth.dto;

import deeplook.server.domain.user.entity.Role;
import deeplook.server.domain.user.entity.User;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String name;


    private String profileUrl;


    private String uid;

    private Role role;

    public SessionUser(User user) {
        this.name = user.getName();
        this.uid = user.getUid();
        this.profileUrl = user.getProfileUrl();
        this.role = user.getRole();
    }
}
