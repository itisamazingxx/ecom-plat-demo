package org.example.infrastructure.repoImpl;

import org.example.Domain.promotion.PromotionDomain;
import org.example.Domain.promotion.PromotionRepository;
import org.example.infrastructure.jooq.tables.Promotion;
import org.example.infrastructure.jooq.tables.records.PromotionRecord;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JooqPromotionRepo implements PromotionRepository {
    @Autowired
    DSLContext dslContext;
    public static final Promotion PROMOTION_T = new Promotion();
    @Override
    public void createPromotion(PromotionDomain promotionDomain) {
        dslContext.executeInsert(toRecord(promotionDomain));
    }
    @Override
    public PromotionDomain getPromotionById(String promotionId) {
        Optional<PromotionDomain> optionalPromotionDomain = dslContext.selectFrom(PROMOTION_T).where(PROMOTION_T.PROMOTION_ID.eq(promotionId)).fetchOptional(this::toDomain);
        return optionalPromotionDomain.orElse(null);
    }
    @Override
    public List<PromotionDomain> getPromotionByStatus(Integer status) {
        return dslContext.selectFrom(PROMOTION_T).where(PROMOTION_T.STATUS.eq(status)).fetch(this::toDomain);
    }

    private PromotionRecord toRecord(PromotionDomain promotionDomain) {
        PromotionRecord promotionRecord = new PromotionRecord();
        promotionRecord.setPromotionName(promotionDomain.getPromotionName());
        promotionRecord.setPromotionId(promotionDomain.getPromotionId());
        promotionRecord.setPromotionPrice(promotionDomain.getPromotionalPrice());
        promotionRecord.setAvailableStock(promotionDomain.getAvailableStock());
        promotionRecord.setCommodityId(promotionDomain.getPromotionId());
        promotionRecord.setEndTime(promotionDomain.getEndTime());
        promotionRecord.setImageUrl(promotionDomain.getImageUrl());
        promotionRecord.setLockStock(promotionDomain.getLockStock());
        promotionRecord.setOriginalPrice(promotionDomain.getOriginalPrice());
        promotionRecord.setStartTime(promotionDomain.getStartTime());
        promotionRecord.setStatus(promotionDomain.getStatus());
        promotionRecord.setTotalStock(promotionDomain.getTotalStock());
        return promotionRecord;
    }
    private PromotionDomain toDomain(PromotionRecord promotionRecord) {
        return PromotionDomain.builder()
                .promotionId(promotionRecord.getPromotionId())
                .commodityId(promotionRecord.getCommodityId())
                .promotionName(promotionRecord.getPromotionName())
                .promotionalPrice(promotionRecord.getPromotionPrice())
                .availableStock(promotionRecord.getAvailableStock())
                .endTime(promotionRecord.getEndTime())
                .startTime(promotionRecord.getStartTime())
                .originalPrice(promotionRecord.getOriginalPrice())
                .status(promotionRecord.getStatus())
                .lockStock(promotionRecord.getLockStock())
                .imageUrl(promotionRecord.getImageUrl())
                .totalStock(promotionRecord.getTotalStock())
                .build();
    }

}
