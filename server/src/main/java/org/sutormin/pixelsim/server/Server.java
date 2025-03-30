package org.sutormin.pixelsim.server;

import java.io.IOException;
import java.net.*;

public class Server {

    private final DatagramSocket socket;
    private final byte[] buf = new byte[256];

    public Server(int port) throws SocketException {
        socket = new DatagramSocket(port);
    }

    public void run() throws IOException {
        boolean running = true;

        while (running) {
            DatagramPacket inPacket
                = new DatagramPacket(buf, buf.length);
            socket.receive(inPacket);

            InetAddress address = inPacket.getAddress();
            int port = inPacket.getPort();
            inPacket = new DatagramPacket(buf, buf.length, address, port);
            String received
                = new String(inPacket.getData(), 0, inPacket.getLength());

            System.out.println("Received: " + received + ", From: " + address.getHostAddress() + ":" + port);

            DatagramPacket outPacket = new DatagramPacket(buf, buf.length, address, port);
            String response = "";

            if (received.equals("stop")) {
                running = false;
                response = "Stopping...";
            } else {
                response = received;
            }

            outPacket.setData(response.getBytes());

            socket.send(outPacket);
        }
        socket.close();
    }
}

/*response = response.substring(4);
            String[] vals = response.split(",");
            for (String val: vals) {
                String[] val2 = val.split(":");
                int id = Integer.parseInt(val2[0]);
                String[] strCoords = val2[1].split("/");
                double[] coords = new double[] {Double.parseDouble(strCoords[0]), Double.parseDouble(strCoords[1])};
                System.out.println("Id: " + id + ", X: " + coords[0] + ", Y: " + coords[1]);
            }*/
