package com.mjaquino;

import com.mjaquino.client.BrainFreezeClient;

public class StudentMain {
    public static void main(String[] args) {
        BrainFreezeClient client = new BrainFreezeClient("192.168.100.14", 8000);
        client.start();
    }
}