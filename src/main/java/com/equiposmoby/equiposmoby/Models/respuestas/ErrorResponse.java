package com.equiposmoby.equiposmoby.Models.respuestas;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ErrorResponse {
    private final String description;

    public ErrorResponse(String description) {
        this.description = description;
    }

    public static ErrorResponse fromRunTimeException(RuntimeException exception) {

        return ErrorResponse.builder().description(exception.getMessage()).build();
    }
}
