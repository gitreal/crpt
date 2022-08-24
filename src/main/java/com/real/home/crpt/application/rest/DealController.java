package com.real.home.crpt.application.rest;

import com.real.home.crpt.application.rest.vo.DealContent;
import com.real.home.crpt.service.ValidationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/rest", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Deal", description = "Deal controller")
public class DealController {

    private final ValidationService validationService;

    @Autowired
    public DealController(final ValidationService validationService) {
        this.validationService = validationService;
    }

    @Operation(summary = "Checking JSON and returns list of validations errors")
    @PutMapping(value = "/Deal/", consumes = APPLICATION_JSON_VALUE)
    public List<String> checkJson(@RequestBody DealContent dealContent) {
        return validationService.validate(dealContent);
    }

    @Operation(summary = "Ping")
    @GetMapping("/Ping/")
    @ApiResponse(responseCode = "200", description = "Success")
    public String ping() {
        return "OK";
    }
}
