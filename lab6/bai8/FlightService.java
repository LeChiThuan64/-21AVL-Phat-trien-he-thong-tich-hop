import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

public interface FlightService extends Remote {
    boolean bookTicket(String flightNumber, int seats) throws RemoteException;
    int availableSeats(String flightNumber) throws RemoteException;   // -1 neu khong co chuyen bay
    boolean cancelBooking(String flightNumber, int seats) throws RemoteException;
    Map<String, Integer> listFlights() throws RemoteException;        // xem tat ca chuyen bay
}
