package com.doctorspractice.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doctorspractice.demo.model.Patient;
import com.doctorspractice.demo.services.PatientServices;

@RestController
@RequestMapping("api/v1/patients")
public class PatientController {
	private PatientServices patientServices;
	
	@Autowired
	public PatientController (PatientServices patientSerivces) {
		this.patientServices = patientSerivces;
		
	}
	
	@GetMapping()
	public ResponseEntity<List<Patient>> getAllPatients() {
        List<Patient> patients = patientServices.getAllPatients();
        return ResponseEntity.ok(patients);
	}
	
	@GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable int id) {
        Optional<Patient> patient = patientServices.getPatientById(id);
        if (patient.isPresent()) {
            return ResponseEntity.ok(patient.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
        Patient savedPatient = patientServices.saveOrUpdatePatient(patient);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPatient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable int id, @RequestBody Patient patient) {
        if (!patientServices.getPatientById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        patient.setId(id);
        Patient updatedPatient = patientServices.saveOrUpdatePatient(patient);
        return ResponseEntity.ok(updatedPatient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable int id) {
        if (!patientServices.getPatientById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        patientServices.deletePatient(id);
        return ResponseEntity.noContent().build();
    }

}
