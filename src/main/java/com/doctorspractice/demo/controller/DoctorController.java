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

import com.doctorspractice.demo.model.Doctor;
import com.doctorspractice.demo.services.DoctorServices;

@RestController
@RequestMapping("api/v1/doctors")
public class DoctorController {
	private DoctorServices doctorServices;
	
	@Autowired
	public DoctorController(DoctorServices doctorServices) {
		this.doctorServices = doctorServices;
		
	}
	
	@GetMapping()
	public ResponseEntity<List<Doctor>> getAllDoctors() {
        List<Doctor> doctors = doctorServices.getAllDoctors();
        return ResponseEntity.ok(doctors);
		
	}
	
	@GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable int id) {
        Optional<Doctor> doctor = doctorServices.getDoctorById(id);
        if (doctor.isPresent()) {
            return ResponseEntity.ok(doctor.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Doctor> createDoctor(@RequestBody Doctor doctor) {
        Doctor savedDoctor = doctorServices.saveOrUpdateDoctor(doctor);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDoctor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable int id, @RequestBody Doctor doctor) {
        if (!doctorServices.getDoctorById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        doctor.setId(id);
        Doctor updatedDoctor = doctorServices.saveOrUpdateDoctor(doctor);
        return ResponseEntity.ok(updatedDoctor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable int id) {
        if (!doctorServices.getDoctorById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        doctorServices.deleteDoctor(id);
        return ResponseEntity.noContent().build();
    }

}
