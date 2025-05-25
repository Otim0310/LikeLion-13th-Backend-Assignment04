package com.likelion.likelionassignment03.patient.api.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record PatientUpdateRequestDto(
        @NotEmpty(message = "수정값이 공백이어서는 안 됩니다.")
        String causes,
        @PositiveOrZero(message = "나이는 0보다 많아야 합니다.")
        @NotNull(message = "수정값이 공백이어서는 안 됩니다.")
        int age
) {
}


