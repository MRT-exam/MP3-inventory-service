package com.MP3.inventoryservice.Event;

import com.MP3.inventoryservice.dto.ResupplyProductDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResupplyDeliveryEvent {
    private List<ResupplyProductDto> resuppliedProductDtos;
}
