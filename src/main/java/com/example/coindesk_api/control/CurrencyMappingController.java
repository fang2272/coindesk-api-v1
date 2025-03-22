package com.example.coindesk_api.control;

  
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
 
import com.example.coindesk_api.service.CurrencyMappingService;
import com.example.coindesk_api.vo.CurrencyMapping;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import java.util.List; 


/**
 * 幣別資料表 CRUD等維護功能的 API。
 */
@Tag(name = "CurrencyMappingController", description = "幣別資料表 CRUD等維護功能的 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/currencies")
public class CurrencyMappingController {
 
    private final CurrencyMappingService service;

    @Operation(summary = "幣別資料表維護功能API", description = "幣別資料表R維護功能的 API")
    @GetMapping
    public List<CurrencyMapping> getAllCurrencies() {
        return service.getAllCurrencies();
    }

    @Operation(summary = "幣別資料表維護功能API", description = "幣別資料表Create維護功能的 API")
    @PostMapping
    public CurrencyMapping addCurrency(@RequestBody CurrencyMapping currency) {
        return service.addCurrency(currency);
    }
    @Operation(summary = "幣別資料表維護功能API", description = "幣別資料表Update維護功能的 API")
    @PutMapping("/{code}")
    public ResponseEntity<CurrencyMapping> updateCurrency(@PathVariable String code, @RequestBody CurrencyMapping currency) {
        return ResponseEntity.ok(service.updateCurrency(code, currency));
    }

    @Operation(summary = "幣別資料表維護功能API", description = "幣別資料表Delete維護功能的 API")
    @DeleteMapping("/{code}")
    public ResponseEntity<String> deleteCurrency(@PathVariable String code) {
        service.deleteCurrency(code);
        return ResponseEntity.ok("Deleted successfully");
    }
}