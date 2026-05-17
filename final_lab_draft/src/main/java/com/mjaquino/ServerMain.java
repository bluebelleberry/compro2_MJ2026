package com.mjaquino;

import com.mjaquino.server.BrainFreezeServer;

public class ServerMain {
    public static void main(String[] args) {
        BrainFreezeServer server = new BrainFreezeServer(8000,"data/questions.json");
        server.start();
    }
}