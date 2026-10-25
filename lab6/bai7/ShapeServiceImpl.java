import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ShapeServiceImpl extends UnicastRemoteObject implements ShapeService {
    public ShapeServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public double rectangleArea(double width, double height) throws RemoteException {
        System.out.println("[Server] Hinh chu nhat: " + width + " x " + height);
        return width * height;
    }

    @Override
    public double circleArea(double radius) throws RemoteException {
        System.out.println("[Server] Hinh tron: r = " + radius);
        return Math.PI * radius * radius;
    }

    @Override
    public double triangleArea(double base, double height) throws RemoteException {
        System.out.println("[Server] Hinh tam giac: day = " + base + ", cao = " + height);
        return 0.5 * base * height;
    }
}
