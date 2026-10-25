import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class ContactServiceImpl extends UnicastRemoteObject implements ContactService {
    private final HashMap<String, String> contacts = new HashMap<>();

    public ContactServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public synchronized void addContact(String name, String phone) throws RemoteException {
        contacts.put(name, phone);
        System.out.println("[Server] Them: " + name + " - " + phone);
    }

    @Override
    public synchronized String findContact(String name) throws RemoteException {
        System.out.println("[Server] Tim: " + name);
        return contacts.get(name);
    }

    @Override
    public synchronized boolean deleteContact(String name) throws RemoteException {
        System.out.println("[Server] Xoa: " + name);
        return contacts.remove(name) != null;
    }

    @Override
    public synchronized boolean updateContact(String name, String newPhone) throws RemoteException {
        if (!contacts.containsKey(name)) return false;
        contacts.put(name, newPhone);
        System.out.println("[Server] Sua: " + name + " -> " + newPhone);
        return true;
    }

    @Override
    public synchronized Map<String, String> listContacts() throws RemoteException {
        return new HashMap<>(contacts); // tra ve ban sao (Serializable)
    }
}
