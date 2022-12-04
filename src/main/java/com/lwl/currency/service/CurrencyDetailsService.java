package com.lwl.currency.service;

import com.lwl.currency.domain.CurrencyDetails;

import java.util.List;

public interface CurrencyDetailsService {

      List<String> getCurrencies();
      CurrencyDetails getCurrencyByCode(String code);

}
