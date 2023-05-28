package deeplook.server.global.common.auth.oauth.dto;

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
    private String oid;
    private String profileImage;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String oid, String profileImage) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.oid = oid;
        this.profileImage = profileImage;
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
                .oid(attributes.get("id").toString())
                .profileImage((String) kakaoProfile.get("profile_image_url"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();

    }
    public User toEntity(String registrationId) {
        return User.builder()
                .name(name)
                .oid(oid)
                .role(Role.USER)
                .profileImage(profileImage)
                .provider(registrationId)
                .build();
    }
}
