package server;

import java.io.*;
import java.net.*;
import java.util.*;

class NewServer extends Thread {

    private LinkedList< DateClient> nameList;
    boolean keepRunning;
    ServerSocket ss;

    MainGUI tata;
    boolean isConnected;
    int port;

    public NewServer(MainGUI t, int p) {
        keepRunning = true;
        isConnected = false;
        tata = t;
        port = p;
    }

    public void closeServer() {
        System.out.println("Am apelat metoda closeServer!");

        int i = 0;
        while (!nameList.isEmpty()) {
            try {
                nameList.get(i).getOutput().writeUTF("QUIT2");
                nameList.get(i).getSocket().close();
                System.out.println("Am inchis socket-ul pentru utilizatorul " + nameList.get(i).getNickName());
                nameList.remove(i);
            } catch (IOException ioe) {
                System.out.println("Can't close socket for a client!");
            }
        }

        try {
            ss.close();
            System.out.println("Am inchis ServerSocket-ul!");
        } catch (IOException ioe) {
            System.out.println("Can't close the SERVER SOCKET!");
        }
    }

    public void addName(String newName, DataInputStream in, DataOutputStream out, Socket s) {
        nameList.add(new DateClient(newName, in, out, s));
    }

    public int numberOfOnlineUsers() {
        return nameList.size();
    }

    public boolean findName(String name) {
        for (int i = 0; i < nameList.size(); i++) {
            if (nameList.get(i).getNickName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public void sendMessage(String userThatSent, String user, String message) throws IOException {
        for (int i = 0; i < nameList.size(); i++) {
            DateClient temp = nameList.get(i);
            if (temp.getNickName().equals(user)) {
                DataOutputStream x = temp.getOutput();
                x.writeUTF("MSG");
                x.writeUTF(userThatSent);
                x.writeUTF(message);
            }
        }
    }

    public void sendBcast(String userThatSent, String message) throws IOException {
        for (int i = 0; i < nameList.size(); i++) {
            DateClient temp = nameList.get(i);
            if (!temp.getNickName().equals(userThatSent)) {
                DataOutputStream x = temp.getOutput();
                x.writeUTF("BCAST");
                x.writeUTF(userThatSent);
                x.writeUTF(message);
            }
        }
    }

    public void list(DataOutputStream out) throws IOException {
        out.writeInt(nameList.size());
        for (int i = 1; i <= nameList.size(); i++) {
            out.writeUTF((nameList.get(i - 1)).getNickName());
        }
    }

    public void remove(String id) {
        int index = 0;
        while (!nameList.get(index).getNickName().equals(id)) {
            index++;
        }
        nameList.remove(index);
        System.out.println("A iesit un utilizator! Useri online momentan: " + numberOfOnlineUsers());
    }

    public void changeNick(String id, String newNick) {
        int index = 0;
        while (!nameList.get(index).getNickName().equals(id)) {
            index++;
        }
        nameList.get(index).setNickName(newNick);
    }

    @Override
    public void run() {

        nameList = new LinkedList< DateClient>();

        try {
            ss = new ServerSocket(port);
            ss.setSoTimeout(1000);
            tata.setConnected(true);
            isConnected = true;
        } catch (IOException ioe) {
            tata.setConnected(false);
            isConnected = false;
            System.out.println("Nu am putut deschide un SERVER SOCKET // Nu am putut seta timeout!");
        }

        System.out.println("Serverul a pornit!");

        while (!ss.isClosed()) {
            Socket cs;
            try {
                cs = ss.accept();
                System.out.println("Conexiune noua! Sunt " + (this.numberOfOnlineUsers() + 1) + " utilizatori conectati!");
                new NewConexiune(this, cs);
            } catch (SocketTimeoutException ste) {

            } catch (IOException ioe) {
                System.out.println("NU POT FACE SS.ACCEPT() // Nu pot face o conexiune noua!");
            }

            //System.out.println("Sunt tot in while!");
        }

        System.out.println(ss.isClosed());
        System.out.println("Am iesit din while-ul serverului!");

    }
}
