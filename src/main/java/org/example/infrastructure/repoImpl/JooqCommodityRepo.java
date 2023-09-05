package org.example.infrastructure.repoImpl;

import org.example.Domain.commodity.CommodityDomain;
import org.example.Domain.commodity.CommodityRepository;
import org.example.infrastructure.jooq.tables.Commodity;
import org.example.infrastructure.jooq.tables.records.CommodityRecord;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JooqCommodityRepo implements CommodityRepository {
    @Autowired
    DSLContext dslContext;
    public static final Commodity COMMODITY_T = new Commodity();

    @Override
    public void createCommodity(CommodityDomain commodityDomain) {
        dslContext.executeInsert(toRecord(commodityDomain));
    }

    @Override
    public CommodityDomain getCommodityById(String commodityId) {
        Optional<CommodityDomain> commodityDomainOptional = dslContext.selectFrom(COMMODITY_T).where(COMMODITY_T.COMMODITY_ID.eq(commodityId)).fetchOptional(this::toDomain);
        return commodityDomainOptional.orElse(null);
    }

    public CommodityRecord toRecord(CommodityDomain commodityDomain) {
        CommodityRecord commodityRecord = new CommodityRecord();
        commodityRecord.setCommodityName(commodityDomain.getCommodityName());
        commodityRecord.setCommodityId(commodityDomain.getCommodityId());
        commodityRecord.setDescription(commodityDomain.getDescription());
        commodityRecord.setPrice(commodityDomain.getPrice());
        commodityRecord.setImgUrl(commodityDomain.getImgUrl());
        return commodityRecord;
    }
    private CommodityDomain toDomain(CommodityRecord commodityRecord) {
        return CommodityDomain.builder()
                .commodityName(commodityRecord.getCommodityName())
                .commodityId(commodityRecord.getCommodityId())
                .price(commodityRecord.getPrice())
                .imgUrl(commodityRecord.getImgUrl())
                .description(commodityRecord.getDescription())
                .build();
    }
}