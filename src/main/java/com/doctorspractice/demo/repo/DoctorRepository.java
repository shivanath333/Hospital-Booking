package com.doctorspractice.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.doctorspractice.demo.model.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer>{

}
