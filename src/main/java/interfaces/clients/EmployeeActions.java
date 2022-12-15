package interfaces.clients;

import exceptions.UserAPIException;
import models.Flight;
import models.FlightFilter;

import java.time.LocalDateTime;
import java.util.List;

public interface EmployeeActions {
    boolean addFlight(int availableSeats, LocalDateTime departureTime, LocalDateTime arrivalTime,
                      String origin, String destination) throws UserAPIException;

    boolean cancelFlight(Flight flight) throws UserAPIException;

    List<Flight> getFlights(FlightFilter filter) throws UserAPIException;
}