import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class FlightServiceImpl extends UnicastRemoteObject implements FlightService {
    private final Map<String, Integer> available = new LinkedHashMap<>(); // so ghe con trong
    private final Map<String, Integer> capacity = new HashMap<>();        // tong so ghe

    public FlightServiceImpl() throws RemoteException {
        super();
        addFlight("VN101", 50);
        addFlight("VN202", 30);
        addFlight("VJ303", 40);
        addFlight("QH404", 20);
    }

    private void addFlight(String code, int seats) {
        available.put(code, seats);
        capacity.put(code, seats);
    }

    @Override
    public synchronized boolean bookTicket(String flightNumber, int seats) throws RemoteException {
        flightNumber = flightNumber.toUpperCase();
        Integer left = available.get(flightNumber);
        if (left == null || seats <= 0 || seats > left) {
            System.out.println("[Server] Dat ve THAT BAI: " + flightNumber + " x" + seats);
            return false;
        }
        available.put(flightNumber, left - seats);
        System.out.println("[Server] Dat " + seats + " ve " + flightNumber + " (con " + (left - seats) + ")");
        return true;
    }

    @Override
    public synchronized int availableSeats(String flightNumber) throws RemoteException {
        Integer left = available.get(flightNumber.toUpperCase());
        return left == null ? -1 : left;
    }

    @Override
    public synchronized boolean cancelBooking(String flightNumber, int seats) throws RemoteException {
        flightNumber = flightNumber.toUpperCase();
        Integer left = available.get(flightNumber);
        // Khong cho huy vuot qua so ghe da dat (left + seats <= capacity)
        if (left == null || seats <= 0 || left + seats > capacity.get(flightNumber)) {
            System.out.println("[Server] Huy ve THAT BAI: " + flightNumber + " x" + seats);
            return false;
        }
        available.put(flightNumber, left + seats);
        System.out.println("[Server] Huy " + seats + " ve " + flightNumber + " (con " + (left + seats) + ")");
        return true;
    }

    @Override
    public synchronized Map<String, Integer> listFlights() throws RemoteException {
        return new LinkedHashMap<>(available);
    }
}
