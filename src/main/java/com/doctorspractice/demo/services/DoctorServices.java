package com.doctorspractice.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.doctorspractice.demo.model.Doctor;
import com.doctorspractice.demo.repo.DoctorRepository;

@Service
public class DoctorServices {
	
	@Autowired
//	public DoctorRepository doctorRespository;
	
//	public Doctor getById(int id) {
//		return doctorRespository.findById(id).orElse(null);
//	}
	
private DoctorRepository doctorRepository;
    
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();   
    }
    
    public Optional<Doctor> getDoctorById(int id) {
        return doctorRepository.findById(id);
    }

    public Doctor saveOrUpdateDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public void deleteDoctor(int id) {
        doctorRepository.deleteById(id);
    }
	
}
