package interfaces.database;

import models.Flight;
import models.FlightFilter;

import java.util.List;

public interface DBHandler {
    List<Flight> findFlights(FlightFilter filter);
    Flight findFlightById(Long id);
}