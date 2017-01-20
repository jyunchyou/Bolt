package com.sql.engine.conn;

/**
 * Created by E450C on 2017/1/20.
 */

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    Socket client;
    PrintWriter pw;

    public Client() throws UnknownHostException, IOException {
        byte[] b=new byte[10];
        client=new Socket("127.0.0.1",8810);
        pw=new PrintWriter(client.getOutputStream());
        InputStream inputStream=System.in;
        StringBuilder stringBuilder=new StringBuilder();
        while(inputStream.read(b)!=-1){

            stringBuilder.append(b);
        }
        pw.write(stringBuilder.toString());
        pw.close();
        inputStream.close();
    }
    public static void main(String[] args) {
        try {
            new Client();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
