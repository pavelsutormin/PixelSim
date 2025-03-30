package org.sutormin.pixelsim.server;

import java.io.IOException;

public class ServerLauncher {
    public static void main(String[] args) throws IOException {
        Server server = new Server(4445);
        server.run();
    }
}
