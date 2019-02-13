package com.booking.definitions;
import java.util.List;


public class Request {
	
	private Integer id;
	private List<Seat> seats =null;
	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}
	public List<Seat> getSeats() {
		return seats;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getId() {
		return id;
	}
	

}
