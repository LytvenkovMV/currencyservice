package com.example.mycurrencycomparator.dto.gif;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String avatar_url;
    private String banner_image;
    private String banner_url;
    private String profile_url;
    private String username;
    private String display_name;
    private String description;
    private String instagram_url;
    private String website_url;
    private String is_verified;
}
