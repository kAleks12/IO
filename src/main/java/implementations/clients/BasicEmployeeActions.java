package implementations.clients;

import exceptions.UserAPIException;
import interfaces.clients.EmployeeActions;
import interfaces.database.DBHandler;
import interfaces.database.EmployeeDBHandler;
import models.Flight;
import models.FlightFilter;

import java.util.List;

public class BasicEmployeeActions implements EmployeeActions {
    private final EmployeeDBHandler handler;

    public BasicEmployeeActions(EmployeeDBHandler handler) {
        this.handler = handler;
    }

    @Override
    public boolean addFlight(Flight flight) throws UserAPIException {
        try {
            return handler.addFlight(flight);
        } catch (Exception e) {
            throw new UserAPIException(e);
        }
    }

    @Override
    public boolean cancelFlight(Flight flight) throws UserAPIException {
        try {
            return handler.deleteFlight(flight);
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
}
