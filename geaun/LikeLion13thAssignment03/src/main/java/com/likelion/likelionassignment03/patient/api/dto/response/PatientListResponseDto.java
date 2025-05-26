package com.likelion.likelionassignment03.patient.api.dto.response;

import lombok.Builder;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Builder
public record PatientListResponseDto(
        List<PatientInfoResponseDto> patients
) {
    public static PatientListResponseDto fromPatientDtoList(List<PatientInfoResponseDto> patients) {
        List<PatientInfoResponseDto> safeList = Objects.requireNonNullElse(patients, Collections.emptyList());
        return PatientListResponseDto.builder()
                .patients(safeList)
                .build();
    }
}
