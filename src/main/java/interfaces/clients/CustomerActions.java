package interfaces.clients;

import exceptions.BadFilterException;
import exceptions.UserAPIException;
import models.Flight;
import models.FlightFilter;
import models.Ticket;
import models.TicketFilter;

import java.util.List;

public interface CustomerActions {
    List<Ticket> getTickets(TicketFilter filter) throws BadFilterException, UserAPIException;

    void buyTicket(Flight flight, String documentID, float price) throws UserAPIException;

    List<Flight> getFlights(FlightFilter filter) throws UserAPIException;

    void cancelTicket(Ticket ticket) throws UserAPIException;

    void rescheduleTicket(Ticket ticket, Flight flight) throws UserAPIException;
}