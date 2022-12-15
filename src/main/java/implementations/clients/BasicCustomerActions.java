package implementations.clients;

import exceptions.UserAPIException;
import interfaces.clients.CustomerActions;
import interfaces.database.CustomerDBHandler;
import models.Flight;
import models.FlightFilter;
import models.Ticket;
import models.TicketFilter;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class BasicCustomerActions implements CustomerActions {
    private final CustomerDBHandler handler;

    public BasicCustomerActions(CustomerDBHandler handler) {
        this.handler = handler;
    }


    private void deleteTicket(Ticket ticket) throws UserAPIException{
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
    public List<Ticket> getTickets(TicketFilter filter, String documentId) throws UserAPIException {
        try {
            return handler.findTickets(filter, documentId);
        } catch (Exception e) {
            throw new UserAPIException(e);
        }
    }

    @Override
    public void buyTicket(Flight desiredFlight, String documentId, float price) throws UserAPIException {
        try {
            var flight = handler.findFlightById(desiredFlight.flightID());

            if (flight.availableSeatsNumber() > 0) {
                var bought = handler.addTicket(flight.flightID(), price, documentId);

                if (!bought) {
                    var errorMessage = """
                            Failed to buy ticket for parameters: %d; %f; %s;
                            """.formatted(flight.flightID(), price, documentId);

                    throw new UserAPIException(errorMessage);
                }
            } else {
                var errorMessage = "Failed to buy ticket, all seats are taken!";
                throw new UserAPIException(errorMessage);
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
            var flight = handler.findFlightById(ticket.getFlightID());
            long days = ChronoUnit.DAYS.between(flight.departureTime(), LocalDateTime.now());

            if (days >= 14) {
                var cancelled = handler.deleteTicket(ticket);

                if (!cancelled) {
                    var errorMessage = "Failed to cancel " + ticket;
                    throw new UserAPIException(errorMessage);
                }
            } else {
                throw new UserAPIException("Ticket cannot be cancelled too little time to departure");
            }

        } catch (Exception e) {
            throw new UserAPIException(e);
        }
    }

    @Override
    public void rescheduleTicket(Ticket ticket, Flight desiredFlight) throws UserAPIException {
        try {
            var flight = handler.findFlightById(desiredFlight.flightID());

            if (flight.availableSeatsNumber() > 0) {
                buyTicket(flight, ticket.getDocumentID(), ticket.getPrice());
                deleteTicket(ticket);
            }
        } catch (Exception e) {
            throw new UserAPIException(e);
        }
    }
}