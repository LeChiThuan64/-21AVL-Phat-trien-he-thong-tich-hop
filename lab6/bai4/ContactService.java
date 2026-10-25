import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

public interface ContactService extends Remote {
    void addContact(String name, String phone) throws RemoteException;
    String findContact(String name) throws RemoteException;          // null neu khong co
    boolean deleteContact(String name) throws RemoteException;
    boolean updateContact(String name, String newPhone) throws RemoteException; // chuc nang "sua"
    Map<String, String> listContacts() throws RemoteException;       // xem toan bo danh ba
}
