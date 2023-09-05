package org.example.Domain.commodity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CommodityDomain {
    private String commodityName;
    private String commodityId;
    private String description;
    private Integer price;
    private String imgUrl;
}

