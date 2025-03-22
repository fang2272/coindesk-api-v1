package com.example.coindesk_api.service;
  
import org.springframework.stereotype.Service;

import com.example.coindesk_api.repository.CurrencyMappingRepository;
import com.example.coindesk_api.vo.CurrencyMapping;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CurrencyMappingService {
 
    private final CurrencyMappingRepository repository;

    public List<CurrencyMapping> getAllCurrencies() {
        return repository.findAll();
    }

    public Optional<CurrencyMapping> getCurrencyByCode(String code) {
        return repository.findByCurrencyCode(code);
    }

    public CurrencyMapping addCurrency(CurrencyMapping currency) {
        return repository.save(currency);
    }

    public CurrencyMapping updateCurrency(String code, CurrencyMapping updatedCurrency) {
        return repository.findByCurrencyCode(code).map(currency -> {
            currency.setCurrencyName(updatedCurrency.getCurrencyName());
            return repository.save(currency);
        }).orElseThrow(() -> new RuntimeException("Currency not found"));
    }

    public void deleteCurrency(String code) {
        repository.findByCurrencyCode(code).ifPresent(repository::delete);
    }
}