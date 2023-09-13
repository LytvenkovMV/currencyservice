package com.example.mycurrencycomparator.dto.gif;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @JsonProperty(value = "avatar_url")
    private String avatarUrl;

    @JsonProperty(value = "banner_image")
    private String bannerImage;

    @JsonProperty(value = "banner_url")
    private String bannerUrl;

    @JsonProperty(value = "profile_url")
    private String profileUrl;

    @JsonProperty(value = "username")
    private String username;

    @JsonProperty(value = "display_name")
    private String displayName;

    @JsonProperty(value = "description")
    private String description;

    @JsonProperty(value = "instagram_url")
    private String instagramUrl;

    @JsonProperty(value = "website_url")
    private String websiteUrl;

    @JsonProperty(value = "is_verified")
    private String isVerified;
}
