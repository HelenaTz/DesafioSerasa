package com.serasa.basec.controller;

import com.serasa.basec.model.HistoricoEvento;
import com.serasa.basec.service.RastreamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/base-c")
@SecurityRequirement(name = "oauth2")
@Tag(name = "Base C", description = "Endpoints para rastreio")
public class BaseCController {

    private final RastreamentoService rastreamentoService;

    public BaseCController(RastreamentoService rastreamentoService) {
        this.rastreamentoService = rastreamentoService;
    }

    @Operation(summary = "Obter última consulta no Bureau")
    @GetMapping("/ultima-consulta/{cpf}")
    public ResponseEntity<HistoricoEvento> getUltimaConsultaBureau(@PathVariable String cpf) {
        HistoricoEvento evento = rastreamentoService.getUltimaConsultaBureau(cpf);
        return evento != null ? ResponseEntity.ok(evento) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Obter última movimentação financeira")
    @GetMapping("/ultima-movimentacao-financeira/{cpf}")
    public ResponseEntity<HistoricoEvento> getUltimaMovimentacao(@PathVariable String cpf) {
        HistoricoEvento evento = rastreamentoService.getUltimaMovimentacaoFinanceira(cpf);
        return evento != null ? ResponseEntity.ok(evento) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Obter última compra com cartão")
    @GetMapping("/ultima-compra/{cpf}")
    public ResponseEntity<HistoricoEvento> getUltimaCompra(@PathVariable String cpf) {
        HistoricoEvento evento = rastreamentoService.getUltimaCompraCartao(cpf);
        return evento != null ? ResponseEntity.ok(evento) : ResponseEntity.notFound().build();
    }
}
