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

}
