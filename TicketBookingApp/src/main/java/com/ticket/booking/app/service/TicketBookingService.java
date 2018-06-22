package com.ticket.booking.app.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticket.booking.app.dao.TicketBookingDao;
import com.ticket.booking.app.entity.Ticket;

@Service
public class TicketBookingService {

	@Autowired
	private TicketBookingDao ticketBookingDao;

	public Ticket createTicket(Ticket ticket) {

		ticket.setBookingDate(new Date().getTime());
		return ticketBookingDao.save(ticket);

	}

	public Optional<Ticket> getTicketById(Integer ticketId) {

		return ticketBookingDao.findById(ticketId);
	}

	public void deleteTicket(Integer ticketId) {
		ticketBookingDao.deleteById(ticketId);
	}

	public Optional<Ticket> updateTicket(Ticket ticket) {

		Integer ticketId = ticket.getTicketId();
		Optional<Ticket> updatedTicket = Optional.empty();

		Optional<Ticket> ticketFromDB = ticketBookingDao.findById(ticketId);
		if (ticketFromDB.isPresent()) {
			updatedTicket = Optional.ofNullable(ticketBookingDao.save(ticket));
		}
		return updatedTicket;
	}

	public List<Ticket> getTicketById() {
		
		List<Ticket> tickets = (List<Ticket>) ticketBookingDao.findAll();
		
		return tickets;
	}

}
