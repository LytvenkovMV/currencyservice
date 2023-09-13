package com.example.mycurrencycomparator.dto.gif;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MetaDto {

    private int status;

    private String msg;

    @JsonProperty(value = "response_id")
    private String responseId;
}
