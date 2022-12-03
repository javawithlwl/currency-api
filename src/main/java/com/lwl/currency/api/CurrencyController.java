package com.lwl.currency.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/currency")
public class CurrencyController {

      @Value("${welcome.message:Welcome to currency controller}")
      private String message;

      @GetMapping("/")
      public String message(){
          return message;
      }

}
