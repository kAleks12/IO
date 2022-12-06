package models;

import java.time.LocalDateTime;

public class TicketFilter {
    private final String documentID;
    private final LocalDateTime departureTime;
    private final LocalDateTime arrivalTime;
    private final String origin;
    private final String destination;

    public TicketFilter(TicketFilterBuilder newFilter) {
        this.documentID = newFilter.documentID;
        this.departureTime = newFilter.departureTime;
        this.arrivalTime = newFilter.arrivalTime;
        this.origin = newFilter.origin;
        this.destination = newFilter.destination;
    }

    public String getDocumentID() {
        return documentID;
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

    public static class TicketFilterBuilder {
        private final String documentID;
        private LocalDateTime departureTime;
        private LocalDateTime arrivalTime;
        private String origin;
        private String destination;

        public TicketFilterBuilder(String documentID) {
            this.documentID = documentID;
        }

        public void setDepartureTime(LocalDateTime departureTime) {
            this.departureTime = departureTime;
        }

        public void setArrivalTime(LocalDateTime arrivalTime) {
            this.arrivalTime = arrivalTime;
        }

        public void setOrigin(String origin) {
            this.origin = origin;
        }

        public void setDestination(String destination) {
            this.destination = destination;
        }

        public TicketFilter build() {
            return new TicketFilter(this);
        }
    }
}