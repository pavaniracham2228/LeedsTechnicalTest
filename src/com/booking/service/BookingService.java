package com.booking.service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.booking.definitions.Request;
import com.booking.definitions.Seat;
import com.booking.helper.JsonInputFileReader;
public class BookingService {
	
	private static int totalSeatsAvailable =24;
	private static int generalSeatsAvailable =20;
	private static int wheelChairSeatsAvailable =2;
	private static List<String> outputList = new ArrayList<String>();
	private static int rowsLength =4;
	private static int columnsLength =6;
	
	private static String seatLayout[][] = new String[rowsLength][columnsLength];
	
	
	public static void main (String[] args) throws IOException{
		List<Request> requestsList = JsonInputFileReader.loadInputJson();
		initializeSeats();
		processRequests(requestsList);
		generateOutput();
	}
	private static void generateOutput(){
		System.out.println("-----output----------");
		for(String output :outputList){
			System.out.println(output);
		}
	}
	public static void initializeSeats(){
		for(int i=0;i<rowsLength;i++){
			
			for(int j=0;j<columnsLength;j++){
				seatLayout[i][j] = "E";
				}
		}
		updateArrayForWheelChairUser();
	}
	public static void updateArrayForWheelChairUser(){
		seatLayout[1][1] ="B";
		seatLayout[1][2] ="B";
		seatLayout[1][3] ="B";
		seatLayout[1][4] ="B";
		
	}
	public static void processRequests(List<Request> requestsList){
		for(Request request :requestsList){
			int totalSeatsRequested =0;
			int wheelChairSeatsRequested =0;
			boolean isWheelChairFriendly =false;
			List<Seat> seatsList =request.getSeats();
			for(Seat seat :seatsList){
				totalSeatsRequested += seat.getSeat();
				if(seat.getType().equals("wheelchair")){
					isWheelChairFriendly = true;
					wheelChairSeatsRequested = seat.getSeat();
				}
			}
			bookTickets(totalSeatsRequested,isWheelChairFriendly,wheelChairSeatsRequested,request);
			
		}
	}
	
	public static void bookTickets(int totalSeatsRequested,boolean isWheelChairFriendly,int wheelChairSeatsRequested,Request request)
	{
		if(isWheelChairFriendly){
			if(wheelChairSeatsAvailable >=wheelChairSeatsRequested)
				bookWheelChairFriendlyTickets(request);
			}
		else{
			if(generalSeatsAvailable >= totalSeatsRequested){
				bookGeneralSeats(request);
			}else if (totalSeatsAvailable >=totalSeatsRequested){
				bookIncludingWheelChair(request);
			}
		}
	}
	public static void bookWheelChairFriendlyTickets(Request request){
		for(Seat seat:request.getSeats()){
			if(seat.getType().equals("wheelchair")){
				while(seat.getSeat()!=0){
					if(seatLayout[1][2].equals("B") && seatLayout[1][1].equals("B")){
						seatLayout[1][2] = "W";
						outputList.add(getSeatNumber(1,2)+":"+"R"+request.getId()+"-"+seat.getType());
										}
					else if(seatLayout[1][3].equals("B") && seatLayout[1][4].equals("B")){
						seatLayout[1][3] = "W";
						outputList.add(getSeatNumber(1,3)+":"+"R"+request.getId()+"-"+seat.getType());
					}
					seat.setSeat(seat.getSeat()-1);
					wheelChairSeatsAvailable--;
					totalSeatsAvailable--;
					
				}
			}
			if(seat.getType().equals("companion")){
				while(seat.getSeat()!=0){
					if(seatLayout[1][1].equals("B")){
						seatLayout[1][1]= "C";
						outputList.add(getSeatNumber(1,1)+":"+"R"+request.getId()+"-"+seat.getType());
					}else if (seatLayout[1][4].equals("B")){
						outputList.add(getSeatNumber(1,4)+":"+"R"+request.getId()+"-"+seat.getType());
					}
					else {
						bookAdditionalCompanionSeat(request);
						
					}
					seat.setSeat(seat.getSeat()-1);
				}
			}
			wheelChairSeatsAvailable--;
			totalSeatsAvailable-=2;
			
		}
	}
	
	private static void bookAdditionalCompanionSeat(Request request){
		for(int i=0;i<rowsLength;i++){
			for(int j=0;j<columnsLength;j++){
				if(seatLayout[i][j].equals("E")){
					seatLayout[i][j] ="companion";
					generalSeatsAvailable--;
					totalSeatsAvailable--;
					String seatNumber =getSeatNumber(i,j);
					outputList.add(seatNumber+":"+"R"+request.getId()+"-companion");
			}
		}
	}
	}
	
	public static void bookGeneralSeats(Request request){
		int resetValue =0;
		for(Seat seat:request.getSeats()){
			for(int i=0;i<rowsLength && seat.getSeat()!=0;i++){
				for(int j=0;j<columnsLength && seat.getSeat()!=0;j++){
				if(resetValue ==3){
					j=3;
					resetValue =0;
				}
				if(seatLayout[i][j].equals("E")){
					seatLayout[i][j] =seat.getType();
					seat.setSeat(seat.getSeat()-1);
					generalSeatsAvailable--;
					totalSeatsAvailable--;
					String seatNumber =getSeatNumber(i,j);
					outputList.add(seatNumber+":"+"R"+request.getId()+"-"+seat.getType());
					if(j==2){
						break;
						
					}else if (j==5){
						resetValue =3;
					}
				}
			}
		}
	}
	
	}
	private static void bookIncludingWheelChair(Request request){
		int resetValue=0;
		for(Seat seat:request.getSeats()){
			for(int i=0;i<rowsLength && seat.getSeat()!=0;i++){
				for(int j=0;j<columnsLength && seat.getSeat()!=0;j++){
					if(resetValue ==3){
						j=3;
						resetValue =0;
					}
					if(seatLayout[i][j].equals("E") ||seatLayout[i][j].equals("B") ){
						
						seatLayout[i][j] =seat.getType();
						seat.setSeat(seat.getSeat()-1);
						generalSeatsAvailable--;
						totalSeatsAvailable--;
						String seatNumber =getSeatNumber(i,j);
						outputList.add(seatNumber+":"+"R"+request.getId()+"-"+seat.getType());
						if(j==2){
							break;
							
						}else if (j==5){
							resetValue =3;
						}
					}
						
				}
			
		}
	}
	
}
	
	private static String getSeatNumber(int i, int j){
		String[] nameArray ={"A","B","C","D"};
		return nameArray[i]+(j+1);
	}
	
	
	
	
		
	
}