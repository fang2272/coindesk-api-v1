package com.example.coindesk_api.control;
 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.coindesk_api.service.CoindeskService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
/**
 *  呼叫coindesk的API
 */
@Tag(name = "實作內容-2", description = " 呼叫coindesk的API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/coindesk")
public class CoindeskController {
 
    private final CoindeskService service;

    @Operation(summary = "實作內容-2: 呼叫coindesk的API", description = "呼叫coindesk的API，得到API內容")
    @GetMapping
    public String getCoindeskData() {
        return service.getCoindeskData();
    }
}
