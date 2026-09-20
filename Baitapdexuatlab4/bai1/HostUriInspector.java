package Baitapdexuatlab4.bai1;



import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;

public class HostUriInspector {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java network.HostUriInspector <hostname> <uri>");
            System.out.println("Vi du : java network.HostUriInspector localhost https://localhost:5000/api?ping=1#top");
            return;
        }

        String hostname = args[0];
        String uriText = args[1];

        inspectHost(hostname);
        System.out.println();
        inspectUri(uriText);
    }

    static void inspectHost(String hostname) {
        System.out.println("=== Thong tin dia chi cho host: " + hostname + " ===");
        try {
            InetAddress[] addresses = InetAddress.getAllByName(hostname);
            for (InetAddress address : addresses) {
                String ipVersion = (address instanceof Inet4Address) ? "IPv4"
                        : (address instanceof Inet6Address) ? "IPv6" : "Khong xac dinh";
                System.out.println("- IP: " + address.getHostAddress());
                System.out.println("  Loai: " + ipVersion);
                System.out.println("  Loopback: " + address.isLoopbackAddress());
                System.out.println("  Site local: " + address.isSiteLocalAddress());
            }
        } catch (UnknownHostException e) {
            System.out.println("LOI: Khong phan giai duoc host '" + hostname + "'");
        }
    }

    static void inspectUri(String uriText) {
        System.out.println("=== Phan tich URI: " + uriText + " ===");
        try {
            URI uri = new URI(uriText);
            System.out.println("Scheme   : " + valueOrNone(uri.getScheme()));
            System.out.println("Host     : " + valueOrNone(uri.getHost()));
            System.out.println("Port     : " + (uri.getPort() == -1 ? "(khong chi dinh, dung port mac dinh)" : String.valueOf(uri.getPort())));
            System.out.println("Path     : " + valueOrNone(uri.getPath()));
            System.out.println("Query    : " + valueOrNone(uri.getQuery()));
            System.out.println("Fragment : " + valueOrNone(uri.getFragment()));
        } catch (URISyntaxException e) {
            System.out.println("LOI: URI khong hop le - " + e.getReason());
        }
    }

    static String valueOrNone(String value) {
        return (value == null || value.isEmpty()) ? "(khong co)" : value;
    }
}