package org.example.Domain.promotion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PromotionDomain {
    String promotionId;
    String promotionName;
    String commodityId;
    Integer originalPrice;
    Integer promotionalPrice;
    LocalDateTime startTime;
    LocalDateTime endTime;
    Integer status;
    Long totalStock;
    Long availableStock;
    Long lockStock;
    String imageUrl;
}
