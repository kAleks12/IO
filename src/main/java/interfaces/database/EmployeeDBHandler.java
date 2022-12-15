package interfaces.database;

import models.Flight;

import java.time.LocalDateTime;

public interface EmployeeDBHandler extends DBHandler {
    boolean deleteFlight(Flight flight);

    boolean addFlight(int availableSeats, LocalDateTime departureTime, LocalDateTime arrivalTime,
                      String origin, String destination);
}
