package com.lwl.currency.repo;

import com.lwl.currency.domain.CurrencyDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CurrencyDetailsRepo extends JpaRepository<CurrencyDetails, UUID> {
  @Query("select c.code from CurrencyDetails c")
  List<String> getCurrencyList();
  Optional<CurrencyDetails> findByCode(String code);
}
