package implementations.clients;

import exceptions.APIException;
import interfaces.clients.EmployeeActions;
import interfaces.database.EmployeeDBHandler;
import models.Flight;
import models.FlightFilter;

import java.time.LocalDateTime;
import java.util.List;

public class BasicEmployeeActions implements EmployeeActions {
    private final EmployeeDBHandler handler;

    public BasicEmployeeActions(EmployeeDBHandler handler) {
        this.handler = handler;
    }

    @Override
    public boolean addFlight(int availableSeats, LocalDateTime departureTime, LocalDateTime arrivalTime,
                             String origin, String destination) throws APIException {
        try {
            return handler.addFlight(availableSeats, departureTime, arrivalTime, origin, destination);
        } catch (Exception e) {
            throw new APIException(e);
        }
    }

    @Override
    public boolean cancelFlight(Flight flight) throws APIException {
        try {
            return handler.deleteFlight(flight);
        } catch (Exception e) {
            throw new APIException(e);
        }
    }

    @Override
    public List<Flight> getFlights(FlightFilter filter) throws APIException {
        try {
            return handler.findFlights(filter);
        } catch (Exception e) {
            throw new APIException(e);
        }
    }
}
