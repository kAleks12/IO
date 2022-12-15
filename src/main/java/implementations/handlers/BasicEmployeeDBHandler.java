package implementations.handlers;

import interfaces.database.EmployeeDBHandler;
import models.Flight;
import models.FlightFilter;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class BasicEmployeeDBHandler implements EmployeeDBHandler {
    private final DataSource ds;

    public BasicEmployeeDBHandler(DataSource ds) {
        this.ds = ds;
    }

    private String toQuery(int id) {
        return "TODO";
    }

    private String toQuery(Flight flight) {
        return "TODO";
    }

    private String toQuery(int availableSeats, LocalDateTime departureTime, LocalDateTime arrivalTime,
                           String origin, String destination) {
        return "TODO";
    }

    private String toQuery(FlightFilter filter) {
        return "TODO";
    }

    private List<Flight> toFlightList(ResultSet results) {
        return Collections.emptyList();
    }

    private Flight toFlight(ResultSet results) {
        return null;
    }

    @Override
    public List<Flight> findFlights(FlightFilter filter) {
        try (
                Connection conn = ds.getConnection();
                Statement statement = conn.createStatement()
        ) {
            var query = toQuery(filter);
            var result = statement.executeQuery(query);

            return toFlightList(result);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Flight findFlightById(int id) {
        try (
                Connection conn = ds.getConnection();
                Statement statement = conn.createStatement()
        ) {
            var query = toQuery(id);
            var result = statement.executeQuery(query);

            return toFlight(result);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteFlight(Flight flight) {
        try (
                Connection conn = ds.getConnection();
                Statement statement = conn.createStatement()
        ) {
            var query = toQuery(flight);
            statement.executeUpdate(query);
        } catch (SQLException e) {
            return false;
        }

        return true;
    }

    @Override
    public boolean addFlight(int availableSeats, LocalDateTime departureTime, LocalDateTime arrivalTime,
                             String origin, String destination) {
        try (
                Connection conn = ds.getConnection();
                Statement statement = conn.createStatement()
        ) {
            var query = toQuery(availableSeats, departureTime, arrivalTime, origin, destination);
            statement.executeUpdate(query);
        } catch (SQLException e) {
            return false;
        }

        return true;
    }
}
