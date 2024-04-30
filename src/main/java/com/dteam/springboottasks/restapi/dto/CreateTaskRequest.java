package com.dteam.springboottasks.restapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateTaskRequest {

    @Schema(example = "Buy groceries")
    @NotBlank
    private String title;


    @Schema(example = "Milk, bread and tomatos")
    @NotBlank
    private String description;
}
