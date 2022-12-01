package models;

import java.time.LocalDateTime;

public record Flight(
        int availableSeatsNumber,
        int flightID,
        LocalDateTime departureTime,
        LocalDateTime arrivalTime,
        String origin,
        String destination) {

}
