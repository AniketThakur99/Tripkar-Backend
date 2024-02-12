package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ErrorResponse;
import com.app.dto.ResponseDTO;
import com.app.pojos.DestinationBooking;
import com.app.services.IDestinationBookingService;

@RestController
@RequestMapping("/booking")
public class DestinationBookingRestController {
	//dependency : service layer i/f
	
	@Autowired
	IDestinationBookingService bookingService;

	public DestinationBookingRestController()
	{
		System.out.println("in ctor "+getClass().getName());
	}
	
	//add REST API endpoint : for getting all usersBooking
	
	@GetMapping
	public List<DestinationBooking> fetchAllUserBooking()
	{
		System.out.println("in fetch all users destination booking");
		return bookingService.getAllBooking();
	}
	
	
	@PostMapping
	public ResponseEntity<?> addNewDestinationBooking(@RequestBody DestinationBooking booking)
	{
		System.out.println("in add user booking "+booking);
		try {
		return new ResponseEntity<>(bookingService.addBooking(booking), HttpStatus.CREATED);
		}catch(RuntimeException e) {
			System.out.println("err in add "+e);
			return new ResponseEntity<>( new ErrorResponse("Adding user destination booking failed!!!!!",e.getMessage() ),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseDTO> deleteDestinationBooking(@PathVariable int id)
	{
		System.out.println("in delete user booking  details " +id);
		return ResponseEntity.ok(new ResponseDTO( bookingService.deleteBooking(id)));
	}
}
