package server;

import java.io.*;
import java.net.*;

class NewConexiune extends Thread {

    Socket cs;
    DataInputStream is;
    DataOutputStream os;
    String name;
    NewServer tata;

    public NewConexiune(NewServer d, Socket client) throws IOException {
        cs = client;
        is = new DataInputStream(cs.getInputStream());
        os = new DataOutputStream(cs.getOutputStream());
        tata = d;
        start();
    }

    @Override
    public void run() {
        try {
            name = is.readUTF();
            while (tata.findName(name) == true) {
                os.writeUTF("TAKEN");
                name = is.readUTF();
            }
            os.writeUTF("Nume bun!");
            tata.addName(name, is, os, cs);

            while (true) {
                String comanda = is.readUTF();
                if (comanda.equals(Actiuni.LIST.getMessage())) {
                    os.writeUTF(comanda);
                    tata.list(os);
                } else if (comanda.equals(Actiuni.QUIT.getMessage())) {
                    os.writeUTF(Actiuni.QUIT.getMessage());
                    tata.remove(name);
                    cs.close();
                } else if (comanda.equals(Actiuni.NICK.getMessage())) {
                    String newNick = is.readUTF();
                    if (tata.findName(newNick)) {
                        os.writeUTF(Actiuni.NICK.getMessage());
                        os.writeUTF("-1");
                    } else {
                        tata.changeNick(name, newNick);
                        os.writeUTF(Actiuni.NICK.getMessage());
                        os.writeUTF(newNick);
                        name = newNick;
                    }
                } else if (comanda.equals(Actiuni.MSG.getMessage())) {
                    String userToSendTo = is.readUTF();
                    String messageToSend = is.readUTF();
                    String userThatSent = name;
                    tata.sendMessage(userThatSent, userToSendTo, messageToSend);
                } else if (comanda.equals(Actiuni.BCAST.getMessage())) {
                    String messageToSend = is.readUTF();
                    String userThatSent = name;
                    tata.sendBcast(userThatSent, messageToSend);
                }
            }
        } catch (Exception e) {
        }
    }
}
