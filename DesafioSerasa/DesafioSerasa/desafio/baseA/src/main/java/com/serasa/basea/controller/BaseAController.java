package com.serasa.basea.controller;

import com.serasa.basea.dto.BaseADto;
import com.serasa.basea.usuario.UsuarioBaseA;
import com.serasa.basea.service.BaseAService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/base-a")
@SecurityRequirement(name = "oauth2")
public class BaseAController {

    private final BaseAService baseAService;

    @Autowired
    public BaseAController(BaseAService baseAService) {
        this.baseAService = baseAService;
    }

    @Operation(summary = "Busca dados sensíveis por CPF")
    @GetMapping("/{cpf}")
    public ResponseEntity<BaseADto> getData(@PathVariable String cpf) throws Exception {
        return ResponseEntity.ok(converteParaResponse(baseAService.getPeloCpf(cpf)));
    }

    private BaseADto converteParaResponse(UsuarioBaseA user) {
        return new BaseADto (
                user.getCpf(),
                user.getNome(),
                user.getEndereco(),
                user.getDividas()
        );
    }
}