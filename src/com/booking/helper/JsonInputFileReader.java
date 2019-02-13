package com.booking.helper;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import com.booking.definitions.Request;
import com.booking.definitions.Seat;


public class JsonInputFileReader {
	
	public static final String  JSON_FILE = "bookingRequest.json";
	public static List<Request> loadInputJson() throws IOException {
		InputStream fis = new FileInputStream(JSON_FILE);
		//create JSonReader object
		JsonReader jsonReader = Json.createReader(fis);
		//get JsonObject from JsonReader
		JsonObject jsonObject = jsonReader.readObject();
		
		//we can close IO resource and JsonReader now
		jsonReader.close();
		fis.close();
		JsonArray requestsJsonArray =jsonObject.getJsonArray("requests");
		List<Request> requestsList = new ArrayList<Request>();
		requestsJsonArray.forEach( request -> parseRequestObject (  (JsonObject) request,requestsList ));
		
		return requestsList;
		
	}
private static void parseRequestObject(JsonObject request, List<Request> requestsList)
{

	int id =request.getInt("id");
	//System.out.println("Id : "+id);
	JsonArray seatsJsonArray =request.getJsonArray("seats");
	List<Seat>seatList = new ArrayList<Seat>();
	seatsJsonArray.forEach( seats -> parseSeatsObject((JsonObject) seats,seatList));
	
	Request reqobj = new Request();
	reqobj.setId(id);
	reqobj.setSeats(seatList);
	requestsList.add(reqobj);
}
private static Seat parseSeatsObject(JsonObject seatsRequest, List<Seat> seatList)
{
	
	Seat seatObj = new Seat();
	int seatNo = seatsRequest.getInt("seat");
	//System.out.println("seatNo :"+seatNo);
	seatObj.setSeat(seatNo);
	String seatType = seatsRequest.getString("type");
	//System.out.println("seatType :"+seatType);
	seatObj.setType(seatType);
	seatList.add (seatObj);
	return seatObj;
	

}
	
	
	
}
