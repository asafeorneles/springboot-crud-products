package com.example.springboot.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

// @NotBlank Validação que garante que a String não pode ser nula nem vazia
// @NotNull Validação que garante o valor não pode ser nulo
public record ProductRecordDto(@NotBlank String name, @NotNull BigDecimal value){
}
