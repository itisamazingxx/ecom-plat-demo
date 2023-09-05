package org.example.Domain.commodity;

public interface CommodityRepository{
    void createCommodity(CommodityDomain commodityDomain);
    CommodityDomain getCommodityById(String commodityId);
}