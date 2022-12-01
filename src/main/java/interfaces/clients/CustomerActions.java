package interfaces.clients;

import exceptions.BadFilterException;
import models.Filter;
import models.Ticket;

import java.util.List;

public interface CustomerActions {
    List<Ticket> viewTickets(Filter filter) throws BadFilterException;
    boolean buyTicket(Ticket ticket);
    boolean cancelTicket(Ticket ticket) throws IllegalArgumentException;
    boolean rescheduleTicket(Ticket ticket) throws IllegalArgumentException;
}