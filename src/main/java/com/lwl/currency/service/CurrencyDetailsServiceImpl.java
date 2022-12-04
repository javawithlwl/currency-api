package com.lwl.currency.service;

import com.lwl.currency.domain.CurrencyDetails;
import com.lwl.currency.dto.CurrencyDetailsDto;
import com.lwl.currency.repo.CurrencyDetailsRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CurrencyDetailsServiceImpl implements  CurrencyDetailsService {

  @Autowired
  private CurrencyDetailsRepo currencyDetailsRepo;

  @Override
  public List<String> getCurrencies() {
    List<String> currencyList = currencyDetailsRepo.getCurrencyList();
    log.info("Currency code list size :{}",currencyList.size());
    return currencyList;
  }

  @Override
  public CurrencyDetailsDto getCurrencyByCode(String code) {
    Assert.hasText(code,"Currency code can not be null or empty");
    Optional<CurrencyDetails> optCurrency = currencyDetailsRepo.findByCode(code);
    CurrencyDetails currencyDetails = optCurrency.orElseThrow(() -> new IllegalArgumentException("Unsupported currency code"));
    return CurrencyDetailsDto.builder()
        .id(currencyDetails.getId())
        .name(currencyDetails.getName())
        .code(currencyDetails.getCode())
        .symbol(currencyDetails.getSymbol())
        .decimalPlaces(currencyDetails.getDecimalPlaces())
        .build();

  }
}
