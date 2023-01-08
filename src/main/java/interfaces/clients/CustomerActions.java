package interfaces.clients;

import exceptions.APIException;
import models.Flight;
import models.FlightFilter;
import models.Ticket;
import models.TicketFilter;

import java.util.List;

public interface CustomerActions {
    List<Ticket> getTickets(TicketFilter filter, String documentId) throws APIException;

    void buyTicket(Flight flight, String documentId, float price) throws APIException;

    List<Flight> getFlights(FlightFilter filter) throws APIException;

    void cancelTicket(Ticket ticket) throws APIException;

    void rescheduleTicket(Ticket ticket, Flight flight) throws APIException;
}