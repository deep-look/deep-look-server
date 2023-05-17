package deeplook.server.global.common.auth.oauth.dto;

import deeplook.server.domain.user.entity.Provider;
import deeplook.server.domain.user.entity.Role;
import deeplook.server.domain.user.entity.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Getter
public class LoginUser implements Serializable, UserDetails {
    private Long id;
    private String name;


    private String profileUrl;


    private String oid;

    private Provider provider;

    private Role role;

    public LoginUser(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.oid = user.getOid();
        this.provider =user.getProvider();
        this.profileUrl = user.getProfileUrl();
        this.role = user.getRole();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> grantedAuthority = new ArrayList<>();
        grantedAuthority.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return getRole().toString();
            }
        });

        return grantedAuthority;
    }

    @Override
    public String getUsername() {
        return oid;
    }
    @Override
    public String getPassword() {
        return "";
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
