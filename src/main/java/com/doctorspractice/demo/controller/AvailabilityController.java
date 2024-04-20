package com.doctorspractice.demo.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.doctorspractice.demo.model.Availability;
import com.doctorspractice.demo.services.AvailabilityServices;

@RestController
//@RequestMapping("api/v1/availability")
public class AvailabilityController {
	
	private final AvailabilityServices availabilityServices;
	
	@Autowired
    public AvailabilityController(AvailabilityServices availabilityService) {
        this.availabilityServices = availabilityService;
    }

    @GetMapping("/api/v1/doctors/{doctorId}/availability")
    public ResponseEntity<List<Availability>> checkAvailability(
            @PathVariable("doctorId") int doctorId,
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam("time") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime time) {

        // Call service method to check availability for the given doctor, date, and time
        List<Availability> availabilities = availabilityServices.checkAvailability(doctorId, date, time);
        
        return ResponseEntity.ok(availabilities);
    }
	
	
}
