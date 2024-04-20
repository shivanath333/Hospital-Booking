package com.doctorspractice.demo.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Appointment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private int  id;
	
	private LocalDate Date;
	
	@JsonIgnore
	 @ManyToOne
	    @JoinColumn(name = "doctor_id")
	    private Doctor doctor;
	    
	    @ManyToOne
	    @JoinColumn(name = "patient_id")
	    private Patient patient;

		public Appointment() {
			super();
			
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public LocalDate getDate() {
			return Date;
		}

		public void setDate(LocalDate date) {
			Date = date;
		}

		public Doctor getDoctor() {
			return doctor;
		}

		public void setDoctor(Doctor doctor) {
			this.doctor = doctor;
		}

		public Patient getPatient() {
			return patient;
		}

		public void setPatient(Patient patient) {
			this.patient = patient;
		}

		@Override
		public String toString() {
			return "Appointment [id=" + id + ", Date=" + Date + ", doctor=" + doctor + ", patient=" + patient + "]";
		}
	    
	    
}
