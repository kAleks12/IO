package implementations.repository;

import implementations.generator.SimpleGenerator;
import interfaces.EntityIdGenerator;
import models.Flight;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;


import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.junit.Assert.*;

@Category(RepositoryTest.class)
public class MemoryRepositoryTest {
    private MemoryRepository<Flight> flightRepository;
    private Flight testFlight1;
    private Flight testFlight2;

    @Before
    public void setUp() {
        testFlight1 = Flight.builder()
                .arrivalTime(LocalDateTime.now())
                .departureTime(LocalDateTime.now().minus(1, ChronoUnit.DAYS))
                .availableSeatsNumber(122)
                .origin("Wroclaw")
                .destination("New York")
                .build();

        testFlight2 = Flight.builder()
                .arrivalTime(LocalDateTime.now())
                .departureTime(LocalDateTime.now().minus(4, ChronoUnit.HOURS))
                .availableSeatsNumber(66)
                .origin("Wroclaw")
                .destination("Barcelona")
                .build();

        EntityIdGenerator generator = new SimpleGenerator();

        flightRepository = new MemoryRepository<>(generator);

        flightRepository.save(testFlight1);
        flightRepository.save(testFlight2);
    }

    @Test
    public void verifyGettingAllFlightFromRepository() {

        var flights = flightRepository.getAll();

        assertEquals(flights.size(), 2);
        assertEquals(flights.get(0), testFlight1);
        assertEquals(flights.get(1), testFlight2);
    }

    @Test
    public void verifyDeletingFlightFromRepository() {
        flightRepository.deleteById(testFlight1.getId());

        var flights = flightRepository.getAll();

        assertEquals(1, flights.size());
        assertEquals(testFlight2, flights.get(0));
    }

}