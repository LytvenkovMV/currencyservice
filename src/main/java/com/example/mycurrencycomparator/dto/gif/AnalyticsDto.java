package com.example.mycurrencycomparator.dto.gif;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnalyticsDto {

    private AnalyticsEventDto onload;
    private AnalyticsEventDto onclick;
    private AnalyticsEventDto onsent;
}
