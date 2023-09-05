package org.example.Domain.promotion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromotionService {
    @Autowired
    PromotionRepository promotionRepository;
    public PromotionDomain createPromotion(PromotionDomain promotionDomain) {
        promotionRepository.createPromotion(promotionDomain);
        return promotionDomain;
    }
    public PromotionDomain getPromotionById(String promotionId) {
        return promotionRepository.getPromotionById(promotionId);
    }
    public List<PromotionDomain> getPromotionByStatus(Integer status) {
        return promotionRepository.getPromotionByStatus(status);
    }
}
