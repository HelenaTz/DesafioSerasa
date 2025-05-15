package com.serasa.baseb.controller;

import com.serasa.baseb.dto.ScoreResponse;
import com.serasa.baseb.service.BaseBService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/base-b")
@SecurityRequirement(name = "oauth2")
public class BaseBController {

    private final BaseBService baseBService;

    public BaseBController(BaseBService baseBService) {
        this.baseBService = baseBService;
    }

    @Operation(summary = "Calcular score de crédito")
    @GetMapping("/score/{cpf}")
    public ResponseEntity<ScoreResponse> getCreditScore(@PathVariable String cpf) {
        ScoreResponse response = baseBService.calculaScore(cpf);
        return ResponseEntity.ok(response);
    }
}