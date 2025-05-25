package com.likelion.likelionassignment03.patient.api;

import com.likelion.likelionassignment03.patient.api.dto.request.PatientSaveRequestDto;
import com.likelion.likelionassignment03.patient.api.dto.request.PatientUpdateRequestDto;
import com.likelion.likelionassignment03.patient.api.dto.response.PatientListResponseDto;
import com.likelion.likelionassignment03.patient.application.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/patient")
public class PatientController {

    private final PatientService patientService;

    @PostMapping("/save")
    public ResponseEntity<String> patientSave(@RequestBody @Valid PatientSaveRequestDto patientSaveRequestDto) {
        patientService.patientSave(patientSaveRequestDto);
        return new ResponseEntity<>("환자 저장!", HttpStatus.CREATED);
    }

    @GetMapping("/{hospitalId}")
    public ResponseEntity<PatientListResponseDto> myPatientFindAll(@PathVariable("hospitalId") Long hospitalId) {
        PatientListResponseDto patientListResponseDto = patientService.patientFindHospital(hospitalId);
        return new ResponseEntity<>(patientListResponseDto, HttpStatus.OK);
    }

    @PatchMapping("/{patientId}")
    public ResponseEntity<String> patientUpdate(
            @PathVariable("patientId") Long patientId,
            @RequestBody PatientUpdateRequestDto patientUPdateRequestDto) {
        patientService.patientUpdate(patientId, patientUPdateRequestDto);
        return new ResponseEntity<>("환자 수정", HttpStatus.OK);
    }

    @DeleteMapping("/{patientId}")
    public ResponseEntity<String> patientDelete(@PathVariable("patientId") Long patientId) {
        patientService.patientDelete(patientId);
        return new ResponseEntity<>("환자 삭제", HttpStatus.OK);
    }
}

