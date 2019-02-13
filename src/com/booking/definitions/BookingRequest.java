package com.booking.definitions;
import java.util.List;


public class BookingRequest {
	
	private List<Request> requests = null;

	public void setRequests(List<Request> requests) {
		this.requests = requests;
	}

	public List<Request> getRequests() {
		return requests;
	}


}
