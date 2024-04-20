package com.doctorspractice.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doctorspractice.demo.model.Patient;
import com.doctorspractice.demo.repo.PatientRepository;

@Service
public class PatientServices {
	
	@Autowired
	public PatientRepository patientRepository;
	
	public List<Patient> getAllPatients(){
		return patientRepository.findAll();
	}
	public Patient getById(int id) {
		return patientRepository.findById(id).orElse(null);
	}
	
	public Optional<Patient> getPatientById(int id) {
        return patientRepository.findById(id);
    }

    public Patient saveOrUpdatePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public void deletePatient(int id) {
        patientRepository.deleteById(id);
    }

}
