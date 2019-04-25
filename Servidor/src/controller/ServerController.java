/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Date;

/**
 *
 * @author Teeu Guima
 */
public class ServerController {
    
    private static ServerController controller;
    
    public static ServerController getInstance(){
        if(controller == null){
            controller = new ServerController();
        }
        return controller;
    }
    
    public void informarHora(Socket cliente){
        System.out.println("Um cliente quer que eu informe a hora");
        this.enviarResposta(cliente, new Date().toString());
    } 

    private void enviarResposta(Socket cliente, String resposta) {
        try {
            DataOutputStream saida = new DataOutputStream(cliente.getOutputStream());
            saida.write(resposta.getBytes());
            saida.flush();
            cliente.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    
    }
    
}
