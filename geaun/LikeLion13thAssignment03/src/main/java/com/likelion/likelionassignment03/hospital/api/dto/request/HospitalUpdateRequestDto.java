package com.likelion.likelionassignment03.hospital.api.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record HospitalUpdateRequestDto(
        @NotEmpty(message = "수정값이 공백이어서는 안 됩니다.")
        String name,
        @NotNull(message = "수정값이 공백이어서는 안 됩니다.")
        String location
) {
}


