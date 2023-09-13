package com.example.mycurrencycomparator.dto.gif;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageFixedDto {

    @ApiModelProperty(value = "Высота", required = true)
    private String height;

    @ApiModelProperty(value = "Ширина", required = true)
    private String width;

    @ApiModelProperty(value = "Размер", required = true)
    private String size;

    @ApiModelProperty(value = "Ссылка", required = true)
    private String url;

    @ApiModelProperty(value = "Размер mp4", required = true)
    @JsonProperty(value = "mp4_size")
    private String mp4Size;

    @ApiModelProperty(value = "Ссылка mp4", required = true)
    private String mp4;

    @ApiModelProperty(value = "Размер webp", required = true)
    @JsonProperty(value = "webp_size")
    private String webpSize;

    @ApiModelProperty(value = "Ссылка webp", required = true)
    private String webp;
}
