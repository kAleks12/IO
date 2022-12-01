package interfaces.database;

import models.Flight;
import models.Ticket;

import java.util.List;

public interface DBHandler {
    List<Ticket> findTickets(String query);
    List<Flight> findFlights(String query);
    boolean deleteTicket(String query);
    boolean updateFlight(Ticket ticket, Flight newFlight);
}