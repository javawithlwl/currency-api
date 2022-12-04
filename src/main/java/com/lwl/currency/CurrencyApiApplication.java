package com.lwl.currency;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lwl.currency.domain.CurrencyDetails;
import com.lwl.currency.dto.CurrencyDto;
import com.lwl.currency.repo.CurrencyDetailsRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootApplication
@Slf4j
public class CurrencyApiApplication {
  @Autowired
  private CurrencyDetailsRepo currencyRepo;
	public static void main(String[] args) {
		SpringApplication.run(CurrencyApiApplication.class, args);
	}

  @Bean
  public CommandLineRunner runner(){
      return (args)->{
          //loadSeedData();
      };

  }

  private void loadSeedData() throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    InputStream inputStream = this.getClass().getResourceAsStream("/Common-Currency.json");
    TypeReference<Map<String, CurrencyDto>> typeReference = new TypeReference<>() {};
    Map<String, CurrencyDto> currencyMap = mapper.readValue(inputStream, typeReference);
    List<CurrencyDetails> currencyDetailsList = convertToCurrencyDetails(currencyMap.values());
    currencyRepo.saveAll(currencyDetailsList);
  }

  private List<CurrencyDetails> convertToCurrencyDetails(Collection<CurrencyDto> values) {
      return values.stream().map(ele->{
          CurrencyDetails obj = new CurrencyDetails();
          obj.setCode(ele.getCode());
          obj.setName(ele.getName());
          obj.setSymbol(ele.getSymbol());
          obj.setDecimalPlaces(ele.getDecimalDigits());
          return obj;
      }).collect(Collectors.toList());
  }

}
