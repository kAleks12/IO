package interfaces.clients;

import models.Filter;
import models.Flight;

import java.util.ArrayList;

public interface EmployeeActions {
    boolean addFlight(Flight flight);
    boolean cancelFlight(Flight flight);
    ArrayList<Flight> viewFlights(Filter filter);
}