package com.serasa.baseb.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "CreditScoreResponse",
        description = "Resposta com o score de crédito calculado para um CPF"
)
public record ScoreResponse(
        @Schema(description = "CPF do usuário", example = "12345678900")
        String cpf,

        @Schema(
                description = "Score de crédito calculado (0-1000)",
                example = "750.0",
                minimum = "0",
                maximum = "1000"
        )
        Double score
) {}