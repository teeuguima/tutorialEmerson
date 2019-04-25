/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import controller.ServerController;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import model.Request;

/**
 *
 * @author Teeu Guima
 */
public class ProtocolProcessor implements Runnable {

    private Socket connection;
    private Object message;

    public ProtocolProcessor(Socket connection) throws ClassNotFoundException {
        this.connection = connection;
        this.message = deserializeObject();
    }

    private Object deserializeObject() throws ClassNotFoundException {
        try {
            InputStream input = new ObjectInputStream(this.connection.getInputStream());

            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            ObjectOutputStream obj = new ObjectOutputStream(bytes);
            try {
                obj.writeObject(((ObjectInputStream) input).readObject());

                bytes.toByteArray();
                return this.deserializeMessage(bytes.toByteArray());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Object deserializeMessage(byte[] data) {
        ByteArrayInputStream message = new ByteArrayInputStream(data);

        try {
            ObjectInput reader = new ObjectInputStream(message);
            return (Object) reader.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void run() {
        if (this.message instanceof Request) {
            Request request = (Request) this.message;
            if (request.getTag().equals("Horário")) {
                ServerController.getInstance().informarHora(this.connection);
            }

        }

    }

}
