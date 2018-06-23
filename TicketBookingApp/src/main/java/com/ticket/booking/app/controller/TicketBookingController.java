package com.ticket.booking.app.controller;

import java.util.List;
import java.util.Optional;

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

import com.ticket.booking.app.entity.Ticket;
import com.ticket.booking.app.service.TicketBookingService;
import com.ticket.booking.dto.Response;

@RestController
@RequestMapping(value = "/ticketBookingApp")
public class TicketBookingController {

	@Autowired
	private TicketBookingService ticketBookingService;

	@PostMapping(value = "/ticket")
	public ResponseEntity<Response> createTicket(@RequestBody Ticket ticket ) {
		Ticket creatTicketRes = ticketBookingService.createTicket(ticket);            
		Response response = new Response(HttpStatus.OK.value(),"Success",creatTicketRes);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	@GetMapping(value = "/ticket/{ticketId}")
	private ResponseEntity<Response> getTicketById(@PathVariable Integer ticketId) {

		ResponseEntity<Response> responseEntity;
		Response response;

		Optional<Ticket> getTicketRes = ticketBookingService.getTicketById(ticketId);

		if (getTicketRes.isPresent()) {
			response = new Response(HttpStatus.OK.value(), "Success", getTicketRes.get());
			responseEntity = new ResponseEntity<Response>(response, HttpStatus.OK);
		} else {
			response = new Response(HttpStatus.BAD_REQUEST.value(), "Invalid Ticket ID", "");
			responseEntity = new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);

		}
		return responseEntity;

	}
	
	@GetMapping(value = "/ticket")
	private ResponseEntity<Response> getAllTicket() {

		ResponseEntity<Response> responseEntity;
		Response response;

		List<Ticket> ticketList = ticketBookingService.getTicketById();

		if (ticketList != null) {
			response = new Response(HttpStatus.OK.value(), "Success", ticketList);
			responseEntity = new ResponseEntity<Response>(response, HttpStatus.OK);
		} else {
			response = new Response(HttpStatus.BAD_REQUEST.value(), "Ticket No Available", "");
			responseEntity = new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);

		}
		return responseEntity;

	}
	
	@DeleteMapping(value="/ticket/{ticketId}")
	private ResponseEntity<Response> deleteTicket(@PathVariable Integer ticketId){
		
		ticketBookingService.deleteTicket(ticketId);
		Response response = new Response(HttpStatus.OK.value(),"Success","");
		return new ResponseEntity<Response>(response,HttpStatus.OK);
		
	}
	
	@PutMapping(value="/ticket")
	private ResponseEntity<Response> updateTicket(@RequestBody Ticket ticket){
		
		Response response ;
		ResponseEntity<Response> responseEntity;

		Optional<Ticket> updatedTicket = ticketBookingService.updateTicket(ticket);
		
		if(updatedTicket.isPresent()){
			response = new Response(HttpStatus.OK.value(),"Success",updatedTicket);
			responseEntity = new ResponseEntity<Response>(response,HttpStatus.OK);
		}else{
			response = new Response(HttpStatus.BAD_REQUEST.value(),"Invalid Ticket ID","");
			responseEntity = new ResponseEntity<Response>(response,HttpStatus.BAD_REQUEST);
			
		}
		
		return responseEntity;
		
	}
}
