package com.doctorspractice.demo.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.doctorspractice.demo.model.Availability;

@Repository
public interface AvailabilityRepository extends JpaRepository<Availability, Integer> {
	
	 List<Availability> findByDoctorIdAndDate(int doctorId, LocalDate date);

}
