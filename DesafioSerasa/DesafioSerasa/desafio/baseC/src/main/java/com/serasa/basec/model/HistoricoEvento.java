package com.serasa.basec.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

@Schema(
        name = "HistoricoEvento",
        description = "Histórico de eventos rastreados na Base C"
)
public record HistoricoEvento(
        @Schema(description = "Tipo do evento", example = "CONSULTA_BUREAU")
        String eventType,

        @Schema(description = "Data e hora do evento", example = "2024-03-15T14:30:00")
        LocalDateTime timestamp,

        @Schema(description = "Detalhes do evento", example = "Consulta realizada no Serasa")
        String details,

        @Schema(description = "Valor", example = "1500.00")
        Double valor,

        @Schema(description = "Estabelecimento ou origem da compra", example = "Loja XYZ")
        String estabelecimento

) implements Comparable<HistoricoEvento> {

    @Override
    public int compareTo(HistoricoEvento other) {
        return this.timestamp.compareTo(other.timestamp);
    }
}