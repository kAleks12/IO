package implementations.generator;

import models.Flight;

public class FlightSignatureGenerator {
    private String getFirst3Chars(String input) {
        if (input.length() > 3) {
            return input.substring(0, 3);
        } else {
            return input;
        }
    }

    public String generateSignature(Flight flight) {
        if (flight == null) {
            throw new IllegalArgumentException();
        }

        if (flight.getArrivalTime() == null || flight.getFlightID() == null || flight.getDepartureTime() == null
                || flight.getOrigin() == null || flight.getDestination() == null) {
            throw new IllegalArgumentException();
        }

        String strFlightId = Long.toString(flight.getFlightID());
        String origin = flight.getOrigin();
        String destination = flight.getDestination();
        String departureDay = Long.toString(flight.getDepartureTime().getDayOfYear());

        String output = getFirst3Chars(strFlightId) +
                getFirst3Chars(origin) +
                getFirst3Chars(destination) +
                getFirst3Chars(departureDay);

        return output;
    }
}
