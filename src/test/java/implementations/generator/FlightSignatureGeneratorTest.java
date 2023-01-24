package implementations.generator;

import models.Flight;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@Category(GeneratorTest.class)
@RunWith(Parameterized.class)
public class FlightSignatureGeneratorTest {
    private FlightSignatureGenerator generator;
    private static final LocalDateTime now = LocalDateTime.now();
    private static final LocalDateTime tomorrow = LocalDateTime.now().plus(1, ChronoUnit.DAYS);
    private static final LocalDateTime yesterday = LocalDateTime.now().minus(1, ChronoUnit.DAYS);

    @Parameterized.Parameter(0)
    public Flight flight;
    @Parameterized.Parameter(1)
    public String expectedOutput;

    @Before
    public void setUp() {
        generator = new FlightSignatureGenerator();
    }

    @Test
    public void shouldCreateValidFlightSignature() {
        String result = generator.generateSignature(flight);

        assertEquals(result, expectedOutput);
    }

    @Test
    public void shouldThrowExceptionWhenFormIsNull() {
        Flight flight = null;

        assertThrows(IllegalArgumentException.class, () -> generator.generateSignature(flight));
    }

    @Test
    public void shouldThrowExceptionWhenFlightIsInvalid() {
        var flight = new Flight(10, 10L, now, tomorrow, null, "Warszawa");

        assertThrows(IllegalArgumentException.class, () -> generator.generateSignature(flight));
    }

    @Parameterized.Parameters
    public static Object[][] generateTestParameters() {

        var flight1 = new Flight(10, 10L, now, tomorrow, "Wroclaw", "Warszawa");
        var flight2 = new Flight(10, 11L, yesterday, tomorrow, "Wroclaw", "Katowice");
        var flight3 = new Flight(10, 12L, yesterday, now, "Katowice", "Warszawa");

        return new Object[][]{{flight1, "10WroWar24"}, {flight2, "11WroKat23"}, {flight3, "12KatWar23"}};
    }
}
