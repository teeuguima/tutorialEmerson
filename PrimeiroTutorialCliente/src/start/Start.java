/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package start;

import controller.ClienteController;

/**
 *
 * @author Teeu Guima
 */
public class Start {

    private static String serverIp = "localhost";
    private static int serverPort = 25356;

    public static void main(String[] args) {

          ClienteController cliente = new ClienteController(serverIp, serverPort);
          
          cliente.getHora();
        
        
    }

}
