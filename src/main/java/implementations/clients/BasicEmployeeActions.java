package implementations.clients;

import exceptions.APIException;
import implementations.generator.FlightSignatureGenerator;
import interfaces.clients.EmployeeActions;
import interfaces.database.EmployeeDBHandler;
import models.Flight;
import models.FlightFilter;

import java.util.List;

public class BasicEmployeeActions implements EmployeeActions {
    private EmployeeDBHandler handler;
    private FlightSignatureGenerator generator;

    public BasicEmployeeActions(EmployeeDBHandler handler, FlightSignatureGenerator generator) {
        this.handler = handler;
        this.generator = generator;
    }

    @Override
    public boolean addFlight(Flight flight) throws APIException {
        try {
            return handler.addFlight(flight);
        } catch (Exception e) {
            throw new APIException(e);
        }
    }

    @Override
    public boolean cancelFlight(Flight flight) throws APIException {
        try {
            return handler.deleteFlight(flight);
        } catch (Exception e) {
            throw new APIException(e);
        }
    }

    @Override
    public List<Flight> getFlights(FlightFilter filter) throws APIException {
        try {
            return handler.findFlights(filter);
        } catch (Exception e) {
            throw new APIException(e);
        }
    }

    @Override
    public List<String> getFlightSignatures() throws APIException {
        var filter = new FlightFilter.FlightFilterBuilder().build();

        return handler.findFlights(filter).stream().map(generator::generateSignature).toList();
    }
}
