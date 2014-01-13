package server;

import java.io.*;
import java.net.*;

public class DateClient {
    
    String nickName;
    DataInputStream input;
    DataOutputStream output;
    Socket socket;
    
    public DateClient(String name, DataInputStream in, DataOutputStream out, Socket s){
        nickName = name;
        input = in;
        output = out;
        socket = s;
    }

    public DataInputStream getInput() {
        return input;
    }

    public String getNickName() {
        return nickName;
    }

    public DataOutputStream getOutput() {
        return output;
    }
    
    public Socket getSocket() {
        return socket;
    }

    public void setInput(DataInputStream input) {
        this.input = input;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setOutput(DataOutputStream output) {
        this.output = output;
    }
    
}
