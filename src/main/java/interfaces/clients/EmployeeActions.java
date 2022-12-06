package interfaces.clients;

import exceptions.UserAPIException;
import models.Flight;
import models.FlightFilter;

import java.util.List;

public interface EmployeeActions {
    boolean addFlight(Flight flight) throws UserAPIException;

    boolean cancelFlight(Flight flight) throws UserAPIException;

    List<Flight> getFlights(FlightFilter filter) throws UserAPIException;
}