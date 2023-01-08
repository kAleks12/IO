package interfaces.clients;

import exceptions.APIException;
import models.Flight;
import models.FlightFilter;

import java.time.LocalDateTime;
import java.util.List;

public interface EmployeeActions {
    boolean addFlight(int availableSeats, LocalDateTime departureTime, LocalDateTime arrivalTime,
                      String origin, String destination) throws APIException;

    boolean cancelFlight(Flight flight) throws APIException;

    List<Flight> getFlights(FlightFilter filter) throws APIException;
}