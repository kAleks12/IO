package implementations.clients;

import exceptions.UserAPIException;
import interfaces.clients.CustomerActions;
import interfaces.database.CustomerDBHandler;
import models.Flight;
import models.FlightFilter;
import models.Ticket;
import models.TicketFilter;

import java.util.List;

public class BasicCustomerActions implements CustomerActions {
    private final CustomerDBHandler handler;

    public BasicCustomerActions(CustomerDBHandler handler) {
        this.handler = handler;
    }

    @Override
    public List<Ticket> getTickets(TicketFilter filter) throws UserAPIException {
        try {
            return handler.findTickets(filter);
        } catch (Exception e) {
            throw new UserAPIException(e);
        }
    }

    @Override
    public void buyTicket(Flight flight, String documentID, float price) throws UserAPIException {
        try {
            var bought = handler.addTicket(flight.flightID(), price, documentID);

            if (!bought) {
                var errorMessage = "Failed to buy ticket for parameters: %d; %f; %s;";
                var filledErrorMessage = String.format(errorMessage, flight.flightID(), price, documentID);

                throw new UserAPIException(filledErrorMessage);
            }
        } catch (Exception e) {
            throw new UserAPIException(e);
        }
    }

    @Override
    public List<Flight> getFlights(FlightFilter filter) throws UserAPIException {
        try {
            return handler.findFlights(filter);
        } catch (Exception e) {
            throw new UserAPIException(e);
        }
    }

    @Override
    public void cancelTicket(Ticket ticket) throws UserAPIException {
        try {
            var cancelled = handler.deleteTicket(ticket);

            if (!cancelled) {
                var errorMessage = "Failed to cancel " + ticket;
                throw new UserAPIException(errorMessage);
            }

        } catch (Exception e) {
            throw new UserAPIException(e);
        }
    }

    @Override
    public void rescheduleTicket(Ticket ticket, Flight flight) throws UserAPIException {
        try {
            cancelTicket(ticket);
            buyTicket(flight, ticket.getDocumentID(), ticket.getPrice());

        } catch (Exception e) {
            throw new UserAPIException(e);
        }
    }
}