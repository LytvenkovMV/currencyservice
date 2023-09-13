package com.example.mycurrencycomparator.dto.gif;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GifDto {

    @JsonProperty(value = "type")
    private String type;

    @JsonProperty(value = "id")
    private String id;

    @JsonProperty(value = "url")
    private String url;

    @JsonProperty(value = "bitly_gif_url")
    private String bitlyGifUrl;

    @JsonProperty(value = "bitly_url")
    private String bitlyUrl;

    @JsonProperty(value = "embed_url")
    private String embedUrl;

    @JsonProperty(value = "username")
    private String username;

    @JsonProperty(value = "source")
    private String source;

    @JsonProperty(value = "title")
    private String title;

    @JsonProperty(value = "rating")
    private String rating;

    @JsonProperty(value = "content_url")
    private String contentUrl;

    @JsonProperty(value = "source_tld")
    private String sourceTld;

    @JsonProperty(value = "source_post_url")
    private String sourcePostUrl;

    @JsonProperty(value = "is_sticker")
    private int isSticker;

    @JsonProperty(value = "import_datetime")
    private String importDateTime;

    @JsonProperty(value = "trending_datetime")
    private String trendingDateTime;

    @JsonProperty(value = "images")
    private ImagesDto images;

    @JsonProperty(value = "user")
    private UserDto user;

    @JsonProperty(value = "analytics_response_payload")
    private String analyticsResponsePayload;

    @JsonProperty(value = "analytics")
    private AnalyticsDto analytics;
}
