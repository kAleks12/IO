package implementations.clients;

import implementations.generator.SimpleGenerator;
import implementations.handlers.BasicEmployeeDBHandler;
import implementations.repository.MemoryRepository;
import interfaces.database.EmployeeDBHandler;
import models.Flight;
import models.FlightFilter;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@Category(ActionsTest.class)
public class BasicEmployeeActionsTest {
    private EmployeeDBHandler dbHandler;
    private BasicEmployeeActions actions;
    private BasicEmployeeDBHandler handler;
    private final LocalDateTime now = LocalDateTime.now();
    private final LocalDateTime tomorrow = LocalDateTime.now().plus(1, ChronoUnit.DAYS);
    private final LocalDateTime yesterday = LocalDateTime.now().minus(1, ChronoUnit.DAYS);

    @Before
    public void setUp() {
        var repository = new MemoryRepository<Flight>(new SimpleGenerator());
        handler = new BasicEmployeeDBHandler(repository);

        handler.addFlight(new Flight(10, 10L, now, tomorrow, "Wroclaw", "Warszawa"));
        handler.addFlight(new Flight(10, 11L, now, tomorrow, "Wroclaw", "Katowice"));
        handler.addFlight(new Flight(10, 12L, tomorrow, tomorrow, "Katowice", "Wroclaw"));
        handler.addFlight(new Flight(10, 13L, yesterday, tomorrow, "Katowice", "Warszawa"));
        handler.addFlight(new Flight(10, 14L, yesterday, tomorrow, "Warszawa", "Katowice"));
        handler.addFlight(new Flight(10, 15L, yesterday, yesterday, "Wroclaw", "Warszawa"));
    }

    @Test
    public void verifyGettingAllFlightsFromDepartureFilter() {
        //arrange
        var tomorrowFilter = new FlightFilter.FlightFilterBuilder()
                .setDepartureTime(tomorrow)
                .build();

        var yesterdayFilter = new FlightFilter.FlightFilterBuilder()
                .setDepartureTime(yesterday)
                .build();

        //act
        var flightsDepartureTomorrow = handler.findFlights(tomorrowFilter);
        var flightsDepartureYesterday = handler.findFlights(yesterdayFilter);

        //assert
        assertEquals(1, flightsDepartureTomorrow.size());
        assertEquals(3, flightsDepartureYesterday.size());

    }

    @Test
    public void verifyGettingAllFlightsFromComplexFilter() {
        //arrange
        var complexFilter = new FlightFilter.FlightFilterBuilder()
                .setOrigin("Wroclaw")
                .setDestination("Warszawa")
                .setArrivalTime(tomorrow)
                .build();

        //act
        var flightsComplexFilter = handler.findFlights(complexFilter);

        //assert
        assertEquals(1, flightsComplexFilter.size());

    }


    @Test
    public void verifyAddingFlight() {
        var flight = new Flight(10, 1L, now, tomorrow, "Wroclaw", "Warszawa");

        handler.addFlight(flight);

        assertEquals(flight, handler.findFlightById(1L));
    }

    @Test
    public void verifyCancellingFlight() {
        var flightToDelete = Flight.builder()
                .flightID(10L)
                .build();

        var flights = handler.findFlights(new FlightFilter.FlightFilterBuilder().build());
        handler.deleteFlight(flightToDelete);
        var newFlights = handler.findFlights(new FlightFilter.FlightFilterBuilder().build());

        assertNull(handler.findFlightById(10L));
        assertEquals(flights.size() - 1, newFlights.size());
    }

    @Test
    public void verifyCancellingFlightWithWrongIdDoesNotChangeState() {
        var flightToDelete = Flight.builder()
                .flightID(0L)
                .build();

        var flights = handler.findFlights(new FlightFilter.FlightFilterBuilder().build());
        handler.deleteFlight(flightToDelete);
        var newFlights = handler.findFlights(new FlightFilter.FlightFilterBuilder().build());

        assertEquals(flights.size(), newFlights.size());
    }
}