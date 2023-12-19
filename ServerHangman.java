import java.io.*;
import java.net.*;

public class ServerHangman {
    public static DatagramSocket ds;
    public static byte buffer[] = new byte[10000];
    public static byte bufferR[] = new byte[5000];
    public static int clientport = 789, serverport = 790;

    public static void main(String args[]) throws Exception {
        ds = new DatagramSocket(clientport);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        InetAddress ia = InetAddress.getByName("localhost");
        while (true) {
            System.out.print("Secret Word: ");
            String str1 = br.readLine();
            System.out.print("Hint: ");
            String str2 = br.readLine();
            String str = str1 + ":" + str2;
            buffer = str.getBytes();
            ds.send(new DatagramPacket(buffer, str.length(), ia, serverport));
            DatagramPacket p = new DatagramPacket(bufferR, bufferR.length);
            ds.receive(p);
            String result = new String(p.getData(), 0, p.getLength());
            System.out.print("Client : " + result + "\n");
            break;
        }
    }
}