package ru.hofftech.consolepackages.model.dto.unloadtruck;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record PackageCountResponse(
        @NotBlank Long packageTypeId,
        Integer count
) {
}
