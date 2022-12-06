package models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Ticket {
    private final String documentID;
    private final int ticketID;
    private int flightID;
    private float price;

//    Ticket(String documentID, int flightID, float price){
//        this.documentID = documentID;
//        this.flightID = flightID;
//        this.price = price;
//    }
}
