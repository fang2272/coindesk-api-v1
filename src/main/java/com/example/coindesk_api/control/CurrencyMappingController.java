package com.example.coindesk_api.control;

  
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
 
import com.example.coindesk_api.service.CurrencyMappingService;
import com.example.coindesk_api.vo.CurrencyMapping;
import com.example.coindesk_api.vo.site24x7.CurrencyMappingDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import java.util.List; 


/**
 * 幣別資料表 CRUD等維護功能的 API。
 */
@Tag(name = "實作內容-1", description = "幣別資料表 CRUD等維護功能的 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/currencies")
public class CurrencyMappingController {
 
    private final CurrencyMappingService service;

    @Operation(summary = "實作內容-1: 幣別資料表維護功能API", 
    		   description = "幣別資料表R維護功能的 API" , 
    		   responses = @ApiResponse(
                           responseCode = "200",
                           description = "Ok",
                        		   content = 
                               { @Content(mediaType = "application/json", schema = 
                                 @Schema(implementation = CurrencyMappingDTO.class)) }
                           )
               )
    @GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CurrencyMappingDTO> getAllCurrencies() {
        return service.getAllCurrenciesV2();
    }

    @Operation(summary = "實作內容-1: 幣別資料表維護功能API", 
    		    description = "幣別資料表Create維護功能的 API" ,
		    		responses = @ApiResponse(
		                    responseCode = "200",
		                    description = "Ok",
		                 		   content = 
		                        { @Content(mediaType = "application/json", schema = 
		                          @Schema(implementation = CurrencyMappingDTO.class)) }
		                    )
		        )
    @PostMapping( produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public CurrencyMappingDTO addCurrency(@RequestBody CurrencyMappingDTO currency) {
        return service.addCurrencyV2(currency);
    }
    @Operation(summary = "實作內容-1: 幣別資料表維護功能API", description = "幣別資料表Update維護功能的 API")
    @PutMapping("/{code}")
    public ResponseEntity<CurrencyMapping> updateCurrency(@PathVariable String code, @RequestBody CurrencyMapping currency) {
        return ResponseEntity.ok(service.updateCurrency(code, currency));
    }

    @Operation(summary = "實作內容-1: 幣別資料表維護功能API", description = "幣別資料表Delete維護功能的 API")
    @DeleteMapping("/{code}")
    public ResponseEntity<String> deleteCurrency(@PathVariable String code) {
        service.deleteCurrency(code);
        return ResponseEntity.ok("Deleted successfully");
    }
}