package com.app.pojos;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class DestinationBooking extends BaseEntity{
	
	private LocalDate checkIn;
	private LocalDate checkOut;
	private String noOfPerson;
	private String destination;
	
}
