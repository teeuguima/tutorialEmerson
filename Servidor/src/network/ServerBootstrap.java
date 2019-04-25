/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Teeu Guima
 */
public class ServerBootstrap {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException {
        ServerSocket socket;
        try {
            socket = new ServerSocket(25356);
            while(true){
                System.out.println("Server running at port: "+ socket.getLocalPort());
                Socket received = socket.accept();
                new Thread(new ProtocolProcessor(received)).start();
            }
        } catch (IOException e) {
        }
    }
    
}
