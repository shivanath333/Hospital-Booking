package com.doctorspractice.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Service;

import com.doctorspractice.demo.model.Appointment;
import com.doctorspractice.demo.repo.AppointmentRepository;

@Service
public class AppointmentServices {
	
	@Autowired
	public AppointmentRepository appointmentRespository;
	private CrudRepository<Appointment, Integer> appointmentRepository;

	public List<Appointment> getAllAppointments(){
		return appointmentRespository.findAll();
		
	}
	
	public Appointment getById(int id) {
		return appointmentRespository.findById(id).orElse(null);
	}
	
	public Appointment createAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

	public Optional<Appointment> getAppointmentById(int id) {
        return appointmentRepository.findById(id);
    }

    public Appointment saveOrUpdateAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public void deleteAppointment(int id) {
        appointmentRepository.deleteById(id);
    }

}
