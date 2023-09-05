package org.example.Domain.commodity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommodityService {
    @Autowired
    CommodityRepository commodityRepository;
    public CommodityDomain createCommodity(CommodityDomain commodityDomain) {
        commodityRepository.createCommodity(commodityDomain);
        return commodityDomain;
    }

    public CommodityDomain getCommodityById(String commodityId) {
        return commodityRepository.getCommodityById(commodityId);
    }
}