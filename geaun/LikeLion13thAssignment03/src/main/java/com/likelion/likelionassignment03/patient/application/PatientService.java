package com.likelion.likelionassignment03.patient.application;

import com.likelion.likelionassignment03.hospital.domain.Hospital;
import com.likelion.likelionassignment03.hospital.domain.repository.HospitalRepository;
import com.likelion.likelionassignment03.patient.api.dto.request.PatientUpdateRequestDto;
import com.likelion.likelionassignment03.patient.api.dto.request.PatientSaveRequestDto;
import com.likelion.likelionassignment03.patient.api.dto.response.PatientInfoResponseDto;
import com.likelion.likelionassignment03.patient.api.dto.response.PatientListResponseDto;
import com.likelion.likelionassignment03.patient.domain.repository.Patient;
import com.likelion.likelionassignment03.patient.domain.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PatientService {

    private final HospitalRepository hospitalRepository;
    private final PatientRepository patientRepository;

    @Transactional
    public void patientSave(PatientSaveRequestDto patientSaveRequestDto) {
        Hospital hospital = hospitalRepository.findById(patientSaveRequestDto.hospitalId())
                .orElseThrow(IllegalArgumentException::new);

        Patient patient = Patient.builder()
                .age(patientSaveRequestDto.age())
                .causes(patientSaveRequestDto.causes())
                .hospital(hospital)
                .build();

        patientRepository.save(patient);
    }

    public PatientListResponseDto patientFindHospital(Long hospitalId) {
        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(IllegalArgumentException::new);

        List<Patient> patients = patientRepository.findByHospital(hospital);
        List<PatientInfoResponseDto> patientInfoResponseDtos = patients.stream()
                .map(PatientInfoResponseDto::from)
                .toList();

        return PatientListResponseDto.from(patientInfoResponseDtos);
    }

    @Transactional
    public void patientUpdate(Long patientId,
                              PatientUpdateRequestDto patientUpdateRequestDto) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(IllegalArgumentException::new);

        patient.update(patientUpdateRequestDto);
    }

    @Transactional
    public void patientDelete(Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(IllegalArgumentException::new);

        patientRepository.delete(patient);
    }
}
