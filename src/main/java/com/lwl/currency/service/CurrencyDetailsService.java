package com.lwl.currency.service;
import com.lwl.currency.dto.CurrencyDetailsDto;

import java.util.List;

public interface CurrencyDetailsService {

      List<String> getCurrencies();
      CurrencyDetailsDto getCurrencyByCode(String code);

}
