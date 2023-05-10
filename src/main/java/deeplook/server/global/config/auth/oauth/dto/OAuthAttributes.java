package deeplook.server.global.config.auth.oauth.dto;

import deeplook.server.domain.user.entity.Role;
import deeplook.server.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {

    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String uid;
    private String profileUrl;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name,String uid, String profileUrl) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.uid = uid;
        this.profileUrl = profileUrl;
    }


    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes){
        if (registrationId.equals("kakao")){
            return ofKaKao("id", attributes);
        }
//        else if (registrationId.equals("google")){
//            return ofgoogle(userNameAttributeName, attributes);
//        }
        return null;
    }
    private static OAuthAttributes ofKaKao(String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> kakaoProfile = (Map<String, Object>) kakaoAccount.get("profile");
        return OAuthAttributes.builder()
                .name((String) kakaoProfile.get("nickname"))
                .uid((String) attributes.get("id"))
                .profileUrl((String) kakaoProfile.get("profile_image_url"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();

    }
    public User toEntity() {
        return User.builder()
                .name(name)
                .uid(uid)
                .role(Role.GUEST)
                .profileUrl(profileUrl)
                .build();
    }
}
