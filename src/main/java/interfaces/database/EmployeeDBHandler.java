package interfaces.database;

import models.Flight;

public interface EmployeeDBHandler extends DBHandler {
    boolean deleteFlight(Flight flight);
}
