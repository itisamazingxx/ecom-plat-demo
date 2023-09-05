package org.example.apiPresentation.commodity;

import org.example.Domain.commodity.CommodityDomain;
import org.example.Domain.commodity.CommodityService;
import org.example.apiPresentation.dto.inDto.CommodityInDto;
import org.example.apiPresentation.dto.outDto.CommodityOutDto;
import org.example.apiPresentation.util.Response;
import org.example.apiPresentation.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/commodity")
public class CommodityController {
    @Autowired
    CommodityService commodityService;

    @PostMapping
    ResponseEntity<CommodityOutDto> createCommodity(@RequestBody CommodityInDto commodityInDto) {
        CommodityDomain commodityDomain = commodityService.createCommodity(toDomain(commodityInDto));
        return ResponseEntity.status(Status.SUCCESS).body(toOutDto(commodityDomain));
    }

    @GetMapping("/id/{commodity_id}")
    ResponseEntity<Response> getCommodityById(@PathVariable("commodity_id") String commodityId) {
        CommodityDomain commodityDomain = commodityService.getCommodityById(commodityId);
        if (Objects.isNull(commodityDomain)) {
            return ResponseEntity.status(Status.BAD_REQUEST).body(Response.builder().message("Wrong Request").build());
        }
        return ResponseEntity.status(Status.SUCCESS).body(Response.builder().result(toOutDto(commodityDomain)).build());
    }

    private CommodityDomain toDomain(CommodityInDto commodityInDto) {
        return CommodityDomain.builder()
                .commodityId(UUID.randomUUID().toString())
                .price(commodityInDto.getPrice())
                .commodityName(commodityInDto.getCommodityName())
                .description(commodityInDto.getDescription())
                .imgUrl(commodityInDto.getImgUrl())
                .build();
    }
    private CommodityOutDto toOutDto(CommodityDomain commodityDomain) {
        return CommodityOutDto.builder()
                .commodityName(commodityDomain.getCommodityName())
                .commodityId(commodityDomain.getCommodityId())
                .description(commodityDomain.getDescription())
                .imgUrl(commodityDomain.getImgUrl())
                .price(commodityDomain.getPrice())
                .build();
    }
}
