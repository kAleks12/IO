package models;

import java.time.LocalDateTime;

public class FlightFilter {
    private final LocalDateTime departureTime;
    private final LocalDateTime arrivalTime;
    private final String origin;
    private final String destination;

    public FlightFilter(FlightFilterBuilder newFilter) {
        this.departureTime = newFilter.departureTime;
        this.arrivalTime = newFilter.arrivalTime;
        this.origin = newFilter.origin;
        this.destination = newFilter.destination;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public static class FlightFilterBuilder {
        private LocalDateTime departureTime;
        private LocalDateTime arrivalTime;
        private String origin;
        private String destination;

        public FlightFilterBuilder setDepartureTime(LocalDateTime departureTime) {
            this.departureTime = departureTime;
            return this;
        }

        public FlightFilterBuilder setArrivalTime(LocalDateTime arrivalTime) {
            this.arrivalTime = arrivalTime;
            return this;
        }

        public FlightFilterBuilder setOrigin(String origin) {
            this.origin = origin;
            return this;
        }

        public FlightFilterBuilder setDestination(String destination) {
            this.destination = destination;
            return this;
        }

        public FlightFilter build() {
            return new FlightFilter(this);
        }
    }
}