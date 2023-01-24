package implementations.handlers;

import interfaces.database.CustomerDBHandler;
import models.*;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Collections;
import java.util.List;

//public class BasicCustomerDBHandler implements CustomerDBHandler {
//    private final DataSource ds;
//
//    public BasicCustomerDBHandler(DataSource ds) {
//        this.ds = ds;
//    }
//
//    private String findFlightQuery(int id) {
//        return "TODO";
//    }
//
//    private String updateSeatsQuery(int id, int newAvailableSeats) {
//        return "TODO";
//    }
//
//    private String findTicketsQuery(TicketFilter filter, String documentId) {
//        return "TODO";
//    }
//
//    private String findFlightsQuery(FlightFilter filter) {
//        return "TODO";
//    }
//
//    private String deleteTicketQuery(Ticket ticket) {
//        return "TODO";
//    }
//
//    private String addTicketQuery(int flightID, float price, String documentID) {
//        return "TODO";
//    }
//
//    private List<Ticket> toTicketList(ResultSet results) {
//        return Collections.emptyList();
//    }
//
//    private List<Flight> toFlightList(ResultSet results) {
//        return Collections.emptyList();
//    }
//
//    private Flight toFlight(ResultSet results) {
//        return null;
//    }
//
//    @Override
//    public List<Ticket> findTickets(TicketFilter filter, String documentId) {
//        try (
//                Connection conn = ds.getConnection();
//                Statement statement = conn.createStatement()
//        ) {
//            var query = findTicketsQuery(filter, documentId);
//            var result = statement.executeQuery(query);
//
//            return toTicketList(result);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Override
//    public boolean deleteTicket(Ticket ticket) {
//        try (
//                Connection conn = ds.getConnection();
//                Statement statement = conn.createStatement()
//        ) {
//            var query = deleteTicketQuery(ticket);
//            statement.executeUpdate(query);
//        } catch (SQLException e) {
//            return false;
//        }
//
//        return true;
//    }
//
//    @Override
//    public boolean addTicket(int flightID, float price, String documentID) {
//        try (
//                Connection conn = ds.getConnection();
//                Statement statement = conn.createStatement()
//        ) {
//            var query = addTicketQuery(flightID, price, documentID);
//            statement.executeUpdate(query);
//        } catch (SQLException e) {
//            return false;
//        }
//
//        return true;
//    }
//
//    @Override
//    public List<Flight> findFlights(FlightFilter filter) {
//        try (
//                Connection conn = ds.getConnection();
//                Statement statement = conn.createStatement()
//        ) {
//            var query = findFlightsQuery(filter);
//            var result = statement.executeQuery(query);
//
//            return toFlightList(result);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Override
//    public Flight findFlightById(int id) {
//        try (
//                Connection conn = ds.getConnection();
//                Statement statement = conn.createStatement()
//        ) {
//            var query = findFlightQuery(id);
//            var result = statement.executeQuery(query);
//
//            return toFlight(result);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Override
//    public boolean takeSeat(int flightId) {
//        try (
//                Connection conn = ds.getConnection();
//                Statement statement = conn.createStatement()
//        ) {
//
//            var flight = toFlight(statement.executeQuery(findFlightQuery(flightId)));
//            var query = updateSeatsQuery(flightId, flight.availableSeatsNumber() - 1);
//
//        } catch (SQLException e) {
//            return false;
//        }
//
//        return true;
//    }
//
//    @Override
//    public boolean freeSeat(int flightId) {
//        try (
//                Connection conn = ds.getConnection();
//                Statement statement = conn.createStatement()
//        ) {
//
//            var flight = toFlight(statement.executeQuery(findFlightQuery(flightId)));
//            var query = updateSeatsQuery(flightId, flight.availableSeatsNumber() + 1);
//
//        } catch (SQLException e) {
//            return false;
//        }
//
//        return true;
//    }
//}
