package interfaces.database;

import models.Ticket;
import models.TicketFilter;

import java.util.List;

public interface CustomerDBHandler extends DBHandler {
    List<Ticket> findTickets(TicketFilter filter, String documentId);

    boolean deleteTicket(Ticket ticket);

    boolean addTicket(int flightId, float price, String documentId);

    boolean takeSeat(int flightId);
    boolean freeSeat(int flightId);
}
