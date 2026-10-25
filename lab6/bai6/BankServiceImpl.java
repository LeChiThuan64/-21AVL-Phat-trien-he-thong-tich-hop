import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class BankServiceImpl extends UnicastRemoteObject implements BankService {
    private double balance;

    public BankServiceImpl(double initialBalance) throws RemoteException {
        super();
        this.balance = initialBalance;
    }

    @Override
    public synchronized double getBalance() throws RemoteException {
        return balance;
    }

    @Override
    public synchronized void deposit(double amount) throws RemoteException {
        if (amount <= 0) {
            throw new RemoteException("So tien gui phai lon hon 0");
        }
        balance += amount;
        System.out.printf("[Server] Gui %.2f -> So du: %.2f%n", amount, balance);
    }

    @Override
    public synchronized boolean withdraw(double amount) throws RemoteException {
        if (amount <= 0 || amount > balance) {
            System.out.printf("[Server] Rut %.2f THAT BAI (so du %.2f)%n", amount, balance);
            return false;
        }
        balance -= amount;
        System.out.printf("[Server] Rut %.2f -> So du: %.2f%n", amount, balance);
        return true;
    }
}
