package implementations.handlers;

import interfaces.database.CustomerDBHandler;
import models.Flight;
import models.FlightFilter;
import models.Ticket;
import models.TicketFilter;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Collections;
import java.util.List;

public class BasicCustomerDBHandler implements CustomerDBHandler {
    private final DataSource ds;

    public BasicCustomerDBHandler(DataSource ds) {
        this.ds = ds;
    }

    private String toQuery(int id) {
        return "TODO";
    }

    private String toQuery(TicketFilter filter, String documentId) {
        return "TODO";
    }

    private String toQuery(FlightFilter filter) {
        return "TODO";
    }

    private String toQuery(Ticket ticket) {
        return "TODO";
    }

    private String toQuery(int flightID, float price, String documentID) {
        return "TODO";
    }

    private List<Ticket> toTicketList(ResultSet results) {
        return Collections.emptyList();
    }

    private List<Flight> toFlightList(ResultSet results) {
        return Collections.emptyList();
    }

    private Flight toFlight(ResultSet results) {
        return null;
    }

    @Override
    public List<Ticket> findTickets(TicketFilter filter, String documentId) {
        try (
                Connection conn = ds.getConnection();
                Statement statement = conn.createStatement()
        ) {
            var query = toQuery(filter, documentId);
            var result = statement.executeQuery(query);

            return toTicketList(result);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteTicket(Ticket ticket) {
        try (
                Connection conn = ds.getConnection();
                Statement statement = conn.createStatement()
        ) {
            var query = toQuery(ticket);
            statement.executeUpdate(query);
        } catch (SQLException e) {
            return false;
        }

        return true;
    }

    @Override
    public boolean addTicket(int flightID, float price, String documentID) {
        try (
                Connection conn = ds.getConnection();
                Statement statement = conn.createStatement()
        ) {
            var query = toQuery(flightID, price, documentID);
            statement.executeUpdate(query);
        } catch (SQLException e) {
            return false;
        }

        return true;
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
}
