package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.With;

import java.time.LocalDateTime;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@With
@Builder
@Data
@EqualsAndHashCode
public class Basket {
    Map<Product, Integer> productQuantityMap;
    Integer daysTillPurchaseFromToday;
}
