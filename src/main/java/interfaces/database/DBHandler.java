package interfaces.database;

import enums.UserRole;
import models.Flight;
import models.FlightFilter;

import java.util.List;

public interface DBHandler {
    List<Flight> findFlights(FlightFilter filter);

    boolean addFlight(Flight flight);
}