package com.lwl.currency.dto;

import lombok.*;

import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CurrencyDetailsDto {
  private UUID id;
  private String code;
  private String name;
  private String symbol;
  private int decimalPlaces;
}
