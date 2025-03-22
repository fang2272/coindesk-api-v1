package com.example.coindesk_api.repository;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.coindesk_api.vo.CurrencyMapping;

import java.util.Optional;

@Repository
public interface CurrencyMappingRepository extends JpaRepository<CurrencyMapping, Long> {
    Optional<CurrencyMapping> findByCurrencyCode(String currencyCode);
}