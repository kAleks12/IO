package implementations.handlers;

import implementations.repository.MemoryRepository;
import interfaces.database.EmployeeDBHandler;
import lombok.AllArgsConstructor;
import models.FlightFilter;
import models.Flight;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@AllArgsConstructor
public class BasicEmployeeDBHandler implements EmployeeDBHandler {
    private final MemoryRepository<Flight> repository;

    @Override
    public List<Flight> findFlights(FlightFilter filter) {

        return repository.getAll().stream()
                .filter(flight -> {
                    if(filter.getArrivalTime() != null) {
                        var flightDate = flight.getArrivalTime().toLocalDate();
                        var filterDate = filter.getArrivalTime().toLocalDate();

                        return flightDate.isEqual(filterDate);
                    }
                    return true;
                })
                .filter(flight -> {
                    if(filter.getDestination() != null) {
                        return flight.getDestination().equals(filter.getDestination());
                    }
                    return true;
                })
                .filter(flight -> {
                    if(filter.getOrigin() != null) {
                        return flight.getOrigin().equals(filter.getOrigin());
                    }
                    return true;
                })
                .filter(flight -> {
                    if(filter.getDepartureTime() != null) {
                        var flightDate = flight.getDepartureTime().toLocalDate();
                        var filterDate = filter.getDepartureTime().toLocalDate();

                        return flightDate.isEqual(filterDate);
                    }
                    return true;
                })
                .toList();
    }

    @Override
    public Flight findFlightById(Long id) {
        var flight = repository.findById(id);

        return flight.orElse(null);
    }

    @Override
    public boolean deleteFlight(Flight flight) {
        repository.deleteById(flight.getId());

        return true;
    }

    @Override
    public boolean addFlight(Flight flight) {
        repository.save(flight);

        return true;
    }
}
