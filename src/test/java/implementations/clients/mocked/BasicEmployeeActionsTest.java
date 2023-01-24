package implementations.clients.mocked;

import exceptions.APIException;
import implementations.clients.ActionsTest;
import implementations.clients.BasicEmployeeActions;
import implementations.handlers.BasicEmployeeDBHandler;
import models.Flight;
import models.FlightFilter;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

@Category(MockedActionTest.class)
@RunWith(MockitoJUnitRunner.class)
public class BasicEmployeeActionsTest {
    @Mock
    private BasicEmployeeDBHandler handler;
    @InjectMocks
    private BasicEmployeeActions actions;
    private final LocalDateTime now = LocalDateTime.now();
    private final LocalDateTime tomorrow = LocalDateTime.now().plus(1, ChronoUnit.DAYS);
    private final LocalDateTime yesterday = LocalDateTime.now().minus(1, ChronoUnit.DAYS);

    private List<Flight> allFlights = new ArrayList<>();
    private List<Flight> tomorrowFlights = new ArrayList<>();
    private List<Flight> yesterdayFlights = new ArrayList<>();
    private List<Flight> complexFlights = new ArrayList<>();

    @Before
    public void setUp() {
        allFlights.add(new Flight(10, 10L, now, tomorrow, "Wroclaw", "Warszawa"));
        allFlights.add(new Flight(10, 11L, now, tomorrow, "Wroclaw", "Katowice"));
        allFlights.add(new Flight(10, 12L, tomorrow, tomorrow, "Katowice", "Wroclaw"));
        allFlights.add(new Flight(10, 13L, yesterday, tomorrow, "Katowice", "Warszawa"));
        allFlights.add(new Flight(10, 14L, yesterday, tomorrow, "Warszawa", "Katowice"));
        allFlights.add(new Flight(10, 15L, yesterday, yesterday, "Wroclaw", "Warszawa"));

        tomorrowFlights.add(allFlights.get(2));

        yesterdayFlights.add(allFlights.get(3));
        yesterdayFlights.add(allFlights.get(4));
        yesterdayFlights.add(allFlights.get(5));

        complexFlights.add(allFlights.get(0));
    }

    @Test
    public void verifyGettingAllFlightsFromDepartureFilter() throws APIException {
        //arrange
        var tomorrowFilter = new FlightFilter.FlightFilterBuilder()
                .setDepartureTime(tomorrow)
                .build();

        var yesterdayFilter = new FlightFilter.FlightFilterBuilder()
                .setDepartureTime(yesterday)
                .build();

        when(handler.findFlights(tomorrowFilter)).thenReturn(tomorrowFlights);
        when(handler.findFlights(yesterdayFilter)).thenReturn(yesterdayFlights);

        //act
        var flightsDepartureTomorrow = actions.getFlights(tomorrowFilter);
        var flightsDepartureYesterday = actions.getFlights(yesterdayFilter);

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

        when(handler.findFlights(complexFilter)).thenReturn(tomorrowFlights);

        //act
        var flightsComplexFilter = handler.findFlights(complexFilter);

        //assert
        assertEquals(1, flightsComplexFilter.size());

    }


    @Test
    public void verifyAddingFlight() {
        var flight = new Flight(10, 1L, now, tomorrow, "Wroclaw", "Warszawa");

        when(handler.addFlight(flight)).thenReturn(true);
        when(handler.findFlightById(1L)).thenReturn(flight);

        handler.addFlight(flight);

        assertEquals(flight, handler.findFlightById(1L));
    }

    @Test
    public void verifyCancellingFlight() {
        var flightToDelete = Flight.builder()
                .flightID(10L)
                .build();

        when(handler.deleteFlight(flightToDelete)).thenReturn(true);
        when(handler.findFlightById(10L)).thenReturn(null);

        handler.deleteFlight(flightToDelete);

        assertNull(handler.findFlightById(10L));
    }
}
