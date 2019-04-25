/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.Socket;
import model.Request;

/**
 *
 * @author Teeu Guima
 */
public class ClienteController {

    private String serverIp;
    private int serverPort;

    public ClienteController(String serverIp, int serverPort) {
        this.serverIp = serverIp;
        this.serverPort = serverPort;
    }

    public void getHora() {
        System.out.println(this.enviarRequisicao(new Request("horario", null)));
    }
    
    public String enviarRequisicao(Request request){
        try {
            Socket socket = new Socket(serverIp, serverPort);
            DataOutputStream saida = new DataOutputStream(socket.getOutputStream());
            saida.write(this.serializeRequest(request));
            saida.flush();
            
            BufferedReader buffer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String response = buffer.readLine();
            socket.close();
            
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public byte[] serializeRequest(Request request){
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        try {
            ObjectOutput out = new ObjectOutputStream(b);
            out.writeObject(request);
            out.flush();
            return b.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
