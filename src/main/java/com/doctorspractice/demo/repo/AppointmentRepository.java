package com.doctorspractice.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.doctorspractice.demo.model.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer>{
	List<Appointment> findByDoctorId(Long doctorId);
	List<Appointment> findByPatientId(Long patientId);

}
