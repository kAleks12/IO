package models;

import interfaces.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@ToString
@Getter
public class Flight implements Entity {
    int availableSeatsNumber;
    Long flightID;
    LocalDateTime departureTime;
    LocalDateTime arrivalTime;
    String origin;
    String destination;

    @Override
    public Long getId() {
        return flightID;
    }

    @Override
    public void setId(Long id) {
        flightID = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight that = (Flight) o;
        return availableSeatsNumber == that.availableSeatsNumber &&
                departureTime.equals(that.departureTime) &&
                arrivalTime.equals(that.arrivalTime) &&
                origin.equals(that.origin) &&
                destination.equals(that.destination);
    }
}
