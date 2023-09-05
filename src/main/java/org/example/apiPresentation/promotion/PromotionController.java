package org.example.apiPresentation.promotion;

import org.example.Domain.promotion.PromotionDomain;
import org.example.Domain.promotion.PromotionService;
import org.example.apiPresentation.dto.inDto.PromotionInDto;
import org.example.apiPresentation.dto.outDto.PromotionOutDto;
import org.example.apiPresentation.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/promotion")
public class PromotionController {
    @Autowired
    PromotionService promotionService;
    @PostMapping
    public ResponseEntity<PromotionOutDto> createPromotion(@RequestBody PromotionInDto promotionInDto) {
        PromotionDomain promotionDomain = promotionService.createPromotion(toDomain(promotionInDto));
        return ResponseEntity.status(Status.SUCCESS).body(toOutDto(promotionDomain));
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<PromotionOutDto> getPromotionById(@PathVariable("id") String promotionId) {
        PromotionDomain promotionDomain = promotionService.getPromotionById(promotionId);
        return ResponseEntity.status(Status.SUCCESS).body(toOutDto(promotionDomain));
    }
    @GetMapping("/status/{status}")
    public List<PromotionOutDto> getPromotionByStatus(@PathVariable("status") Integer status) {
        List<PromotionDomain> promotionDomainList = promotionService.getPromotionByStatus(status);
        return promotionDomainList.stream().map(this::toOutDto).collect(Collectors.toList());
    }

    private PromotionDomain toDomain(PromotionInDto promotionInDto) {
        return PromotionDomain.builder()
                .promotionId(UUID.randomUUID().toString())
                .commodityId(promotionInDto.getCommodityId())
                .promotionName(promotionInDto.getPromotionName())
                .promotionalPrice(promotionInDto.getPromotionalPrice())
                .availableStock(promotionInDto.getAvailableStock())
                .endTime(promotionInDto.getEndTime())
                .startTime(promotionInDto.getStartTime())
                .originalPrice(promotionInDto.getOriginalPrice())
                .status(promotionInDto.getStatus())
                .lockStock(promotionInDto.getLockStock())
                .imageUrl(promotionInDto.getImageUrl())
                .totalStock(promotionInDto.getTotalStock())
                .build();
    }
    private PromotionOutDto toOutDto(PromotionDomain promotionDomain) {
        return PromotionOutDto.builder()
                .promotionId(promotionDomain.getPromotionId())
                .commodityId(promotionDomain.getCommodityId())
                .promotionName(promotionDomain.getPromotionName())
                .promotionalPrice(promotionDomain.getPromotionalPrice())
                .availableStock(promotionDomain.getAvailableStock())
                .endTime(promotionDomain.getEndTime())
                .startTime(promotionDomain.getStartTime())
                .originalPrice(promotionDomain.getOriginalPrice())
                .status(promotionDomain.getStatus())
                .lockStock(promotionDomain.getLockStock())
                .imageUrl(promotionDomain.getImageUrl())
                .totalStock(promotionDomain.getTotalStock())
                .build();
    }
}
