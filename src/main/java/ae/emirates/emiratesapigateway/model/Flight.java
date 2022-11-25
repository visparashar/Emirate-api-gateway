package ae.emirates.emiratesapigateway.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// created a dummy dto

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Flight {
	
	String number;
	String seatCapacity;
	String fromAirport;
	String toAirport;
	Date date;

}
