package com.example.mycurrencycomparator.dto.gif;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaginationDto {

    @JsonProperty(value = "total_count")
    private int totalCount;

    private int count;

    private int offset;
}
