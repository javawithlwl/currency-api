package com.lwl.currency.api;
import com.lwl.currency.dto.CurrencyDetailsDto;
import com.lwl.currency.service.CurrencyDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/currency")
public class CurrencyController {

      @Autowired
      private CurrencyDetailsService currencyDetailsService;

      @Value("${welcome.message:Welcome to currency controller}")
      private String message;

      @GetMapping("/")
      public String message(){
          return message;
      }
      @GetMapping("/all")
      public ResponseEntity<List<String>> getCurrencies(){
            return  ResponseEntity.ok(currencyDetailsService.getCurrencies());
      }

      @GetMapping("/{code}")
      public ResponseEntity<CurrencyDetailsDto> getCurrencyDetails(@PathVariable("code")String code){
            return ResponseEntity.ok(currencyDetailsService.getCurrencyByCode(code));
      }

}
