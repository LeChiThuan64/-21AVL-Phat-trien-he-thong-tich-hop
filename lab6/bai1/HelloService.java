import java.rmi.Remote;
import java.rmi.RemoteException;

/** Interface tu xa: moi phuong thuc phai throws RemoteException */
public interface HelloService extends Remote {
    String sayHello() throws RemoteException;
}
