package com.doctorspractice.demo.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doctorspractice.demo.model.Availability;
import com.doctorspractice.demo.repo.AvailabilityRepository;

@Service
public class AvailabilityServices {
	
	private final AvailabilityRepository availabilityRepository;

    @Autowired
    public AvailabilityServices(AvailabilityRepository availabilityRepository) {
        this.availabilityRepository = availabilityRepository;
    }
    
    public List<Availability> checkAvailability(int doctorId, LocalDate date, LocalTime time) {
        // Retrieve the list of availabilities for the given doctor and date
        List<Availability> availabilities = availabilityRepository.findByDoctorIdAndDate(doctorId, date);
        
        // Filter the availabilities based on the provided time
        // Here, we're assuming that each availability has a start time and an end time
        // You may need to adjust this logic based on your database schema
        for (Availability availability : availabilities) {
            if (time.isAfter(availability.getStartTime()) && time.isBefore(availability.getEndTime())) {
                // Time slot is available, add it to the result list
                // You can customize this logic based on your requirements
                // For example, you may need to check if the time slot is booked by another appointment
                // or if there are any other constraints
                return List.of(availability);
            }
        }
        
        // No available time slots found for the given doctor and time
        return List.of();
    }


}
