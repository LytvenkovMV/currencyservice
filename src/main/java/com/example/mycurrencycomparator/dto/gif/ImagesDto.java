package com.example.mycurrencycomparator.dto.gif;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImagesDto {

    @ApiModelProperty(value = "Гифка оригинального размера", required = true)
    private ImageOriginalDto original;

    @ApiModelProperty(value = "Гифка фиксированной высоты", required = true)
    @JsonProperty(value = "fixed_height")
    private ImageFixedDto fixedHeight;

    @ApiModelProperty(value = "Гифка фиксированной высоты с пониженной дискретизацией", required = true)
    @JsonProperty(value = "fixed_height_downsampled")
    private ImageFixedDownsampledDto fixedHeightDownsampled;

    @ApiModelProperty(value = "Гифка фиксированной высоты малого размера", required = true)
    @JsonProperty(value = "fixed_height_small")
    private ImageFixedDto fixedHeightSmall;

    @ApiModelProperty(value = "Гифка фиксированной ширины", required = true)
    @JsonProperty(value = "fixed_width")
    private ImageFixedDto fixedWidth;

    @ApiModelProperty(value = "Гифка фиксированной ширины с пониженной дискретизацией", required = true)
    @JsonProperty(value = "fixed_width_downsampled")
    private ImageFixedDownsampledDto fixedWidthDownsampled;

    @ApiModelProperty(value = "Гифка фиксированной ширины малого размера", required = true)
    @JsonProperty(value = "fixed_width_small")
    private ImageFixedDto fixedWidthSmall;
}
