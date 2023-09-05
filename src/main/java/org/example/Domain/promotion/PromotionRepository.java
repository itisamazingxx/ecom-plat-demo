package org.example.Domain.promotion;

import java.util.List;

public interface PromotionRepository {
    void createPromotion(PromotionDomain promotionDomain);
    PromotionDomain getPromotionById(String promotionId);
    List<PromotionDomain> getPromotionByStatus(Integer status);
}

