package com.app.services;

import java.util.List;

import com.app.pojos.DestinationBooking;

public interface IDestinationBookingService {
	List<DestinationBooking> getAllBooking();
	DestinationBooking addBooking(DestinationBooking booking);
	String deleteBooking(long id);
}
