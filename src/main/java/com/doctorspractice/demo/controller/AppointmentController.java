package com.doctorspractice.demo.controller;

import java.net.URI;
import java.util.List;

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

import com.doctorspractice.demo.model.Appointment;
import com.doctorspractice.demo.services.AppointmentServices;

@RestController
@RequestMapping("/api/v1/appointments")
public class AppointmentController {
	
	private AppointmentServices appointmentService;

	@Autowired
	public AppointmentController(AppointmentServices appointmentService) {
		this.appointmentService = appointmentService;		
	}
	
	@GetMapping()
	public ResponseEntity<List<Appointment>> getAllAppointments() {
        List<Appointment> appointments = appointmentService.getAllAppointments();
        return ResponseEntity.ok(appointments);
		
	}
	
    @PostMapping("/book")
    public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment) {
        Appointment savedAppointment = appointmentService.saveOrUpdateAppointment(appointment);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAppointment);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Appointment> updateAppointment(@PathVariable int id, @RequestBody Appointment appointment) {
        if (!appointmentService.getAppointmentById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        appointment.setId(id);
        Appointment updatedAppointment = appointmentService.saveOrUpdateAppointment(appointment);
        return ResponseEntity.ok(updatedAppointment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable int id) {
        if (!appointmentService.getAppointmentById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        appointmentService.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }

}
