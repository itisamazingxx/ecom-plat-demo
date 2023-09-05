package org.example.apiPresentation.dto.outDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CommodityOutDto {
    private String commodityName;
    private String commodityId;
    private String description;
    private Integer price;
    private String imgUrl;

}
