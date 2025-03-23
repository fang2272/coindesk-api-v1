package com.example.coindesk_api.service;
  
import org.springframework.stereotype.Service;

import com.example.coindesk_api.repository.CurrencyMappingRepository;
import com.example.coindesk_api.vo.CurrencyMapping;
import com.example.coindesk_api.vo.site24x7.CurrencyMappingDTO;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;

@Service
@RequiredArgsConstructor
public class CurrencyMappingService {
 
    private final CurrencyMappingRepository repository;

    @Deprecated
    public List<CurrencyMapping> getAllCurrencies() { 
        return repository.findAll();
    }
    public List<CurrencyMappingDTO> getAllCurrenciesV2() {
		List<CurrencyMappingDTO> result = new ArrayList<>();
    	repository.findAll().forEach(unit ->{
    		CurrencyMappingDTO target = new CurrencyMappingDTO();
    		BeanUtils.copyProperties(unit, target); 
    		result.add(target);
    	});   
       return result;
   }

    public Optional<CurrencyMapping> getCurrencyByCode(String code) {
        return repository.findByCurrencyCode(code);
    }

    @Deprecated
    public CurrencyMapping addCurrency(CurrencyMapping currency) {
        return repository.save(currency);
    }
    public CurrencyMappingDTO addCurrencyV2(CurrencyMappingDTO currency) {
    	CurrencyMapping target = new CurrencyMapping();
    	BeanUtils.copyProperties(currency, target);
    	CurrencyMapping result = repository.save(target);
    	currency.setId(result.getId());
        return currency;
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