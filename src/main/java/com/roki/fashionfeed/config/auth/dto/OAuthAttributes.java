package com.roki.fashionfeed.config.auth.dto;

import com.roki.fashionfeed.domain.user.Role;
import com.roki.fashionfeed.domain.user.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {
    private static final String NAME = "name";
    private static final String EMAIL = "email";
    private static final String NICKNAME = "nickname";
    private static final String GOOGLE_PROFILE_IMAGE_SRC = "/images/google_profile_image.PNG";
    private static final String FACEBOOK_PROFILE_IMAGE_SRC = "/images/facebook_profile_image.PNG";
    private static final String KAKAO_PROFILE_IMAGE_SRC = "/images/kakao_profile_image.PNG";
    private static final String NAVER_PROFILE_IMAGE_SRC = "/images/naver_profile_image.PNG";
    private static final String NAVER = "naver";
    private static final String KAKAO = "kakao";
    private static final String FACEBOOK = "facebook";
    private static final String ID = "id";

    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String picture;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String picture) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }

    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        if (NAVER.equals(registrationId)) {
            return ofNaver(ID, attributes);
        }

        if (KAKAO.equals(registrationId)) {
            return ofKakao(userNameAttributeName, attributes);
        }

        if (FACEBOOK.equals(registrationId)) {
            return ofFacebook(userNameAttributeName, attributes);
        }

        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .name((String) attributes.get(NAME))
                .email((String) attributes.get(EMAIL))
                .picture(GOOGLE_PROFILE_IMAGE_SRC)
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    private static OAuthAttributes ofFacebook(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .name((String) attributes.get(NAME))
                .email((String) attributes.get(EMAIL))
                .picture(FACEBOOK_PROFILE_IMAGE_SRC)
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    private static OAuthAttributes ofKakao(String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> properties = ((Map<String, Object>) attributes.get("properties"));
        Map<String, Object> kakaoAccount = ((Map<String, Object>) attributes.get("kakao_account"));

        return OAuthAttributes.builder()
                .name((String) attributes.get(NICKNAME))
                .email((String) kakaoAccount.get(EMAIL))
                .picture(KAKAO_PROFILE_IMAGE_SRC)
                .attributes(properties)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        return OAuthAttributes.builder()
                .name((String) response.get(NAME))
                .email((String) response.get(EMAIL))
                .picture(NAVER_PROFILE_IMAGE_SRC)
                .attributes(response)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public User toEntity() {
        return User.builder()
                .name(name)
                .email(email)
                .picture(picture)
                .role(Role.GUEST)
                .build();
    }
}
