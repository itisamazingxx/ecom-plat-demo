package org.example.apiPresentation.dto.inDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CommodityInDto {
    private String commodityName;
    private String description;
    private Integer price;
    private String imgUrl;

}
