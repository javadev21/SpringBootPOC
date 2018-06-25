package com.ticket.booking.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="ticket")
public class Ticket {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ticketId")
	private int ticketId;
	
	@Column(name="passengerName")
	@NotEmpty
	private String passengerName;
	
	@Column(name="bookingDate")
	private long bookingDate;
	
	@Column(name="sourceStation")
	@NotEmpty
	private String sourceStation;
	
	@Column(name="destStation")
	@NotEmpty

	private String destStation;
	
	@Column(name="email")
	@NotEmpty
	@Email
	private String email;

	public int getTicketId() {
		return ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	public long getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(long bookingDate) {
		this.bookingDate = bookingDate;
	}

	public String getSourceStation() {
		return sourceStation;
	}

	public void setSourceStation(String sourceStation) {
		this.sourceStation = sourceStation;
	}

	public String getDestStation() {
		return destStation;
	}

	public void setDestStation(String destStation) {
		this.destStation = destStation;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Ticket() {
		super();
	}

	public Ticket(int ticketId, String passengerName, long bookingDate, String sourceStation, String destStation,
			String email) {
		super();
		this.ticketId = ticketId;
		this.passengerName = passengerName;
		this.bookingDate = bookingDate;
		this.sourceStation = sourceStation;
		this.destStation = destStation;
		this.email = email;
	}

	@Override
	public String toString() {
		return "Ticket [ticketId=" + ticketId + ", passengerName=" + passengerName + ", bookingDate=" + bookingDate
				+ ", sourceStation=" + sourceStation + ", destStation=" + destStation + ", email=" + email + "]";
	}
	
	
	

}
