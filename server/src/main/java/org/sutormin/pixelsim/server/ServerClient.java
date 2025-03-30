package org.sutormin.pixelsim.server;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.DatagramPacket;
import java.io.IOException;

public class ServerClient {
    public static void main(String[] args) {
        try {
            // Create a datagram socket
            DatagramSocket socket = new DatagramSocket();

            // Get the server address
            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = 4445;

            String response = "";
            int amountCorrect = 0;
            for (int i = 0; i < 10; i++) {
                long startTime = System.currentTimeMillis();
                // Create a datagram packet
                String send = String.valueOf(Math.round(Math.random() * 10000));
                byte[] sendData = send.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);

                // Send the packet
                socket.send(sendPacket);

                // Prepare to receive the response
                byte[] receiveData = new byte[256];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

                // Receive the response
                socket.receive(receivePacket);

                // Print the response
                response = new String(receivePacket.getData(), 0, receivePacket.getLength());
                boolean correct = response.equals(send);
                if (!correct) {
                    System.out.println("Expected: " + send + ", Actual: " + response);
                } else {
                    amountCorrect += 1;
                }
                /*if (response.startsWith("pos_")) {
                    break;
                }*/
            }
            System.out.println(amountCorrect);
            /*response = response.substring(4);
            String[] vals = response.split(",");
            for (String val: vals) {
                String[] val2 = val.split(":");
                int id = Integer.parseInt(val2[0]);
                String[] strCoords = val2[1].split("/");
                double[] coords = new double[] {Double.parseDouble(strCoords[0]), Double.parseDouble(strCoords[1])};
                System.out.println("Id: " + id + ", X: " + coords[0] + ", Y: " + coords[1]);
            }*/

            // Close the socket
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
