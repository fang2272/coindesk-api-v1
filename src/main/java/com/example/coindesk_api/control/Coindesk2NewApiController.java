package com.example.coindesk_api.control;
 
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.coindesk_api.service.CoindeskService;
import com.example.coindesk_api.vo.new_dto.CoinDeskNewResponse; 

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
/**
 *  呼叫coindesk的API，並進行資料轉換，組成新API
 */
@Tag(name = "實作內容-3", description = " 呼叫coindesk的API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/coindesk_new")
public class Coindesk2NewApiController {
 
    private final CoindeskService service;

    @Operation(summary = "實作內容-3:呼叫coindesk的API，並進行資料轉換，組成新API。 ", 
    		   description = "此新API包含以下內容： A. 更新時間（時間格式範例：1990/01/01 00:00:00）。 B. 幣別相關資訊（幣別，幣別中文名稱，以及匯率）。" ,
    		   responses = @ApiResponse(
                       responseCode = "200",
                       description = "Ok",
                    		   content = 
                           { @Content(mediaType = "application/json", schema = 
                             @Schema(implementation = CoinDeskNewResponse.class)) }
                       )
           )
    @GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)
    public CoinDeskNewResponse getCoindeskData() {
        return service.newLogic();
    }
}
