package com.sql.engine.conn;
import com.sql.engine.analysis.Analysis;
import com.sql.engine.memory.BplusTree;
import com.sql.engine.memory.BplusTreeI;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private Socket socket;
    private ServerSocket ss;
    private BplusTreeI tree;
    public Server() throws IOException {
        tree=new BplusTree(200);
        ss = new ServerSocket(8810);
        byte[] b=new byte[10];
        StringBuilder stringBuilder=new StringBuilder();
        while (true) {
            socket = ss.accept();
            InputStream inputStream=socket.getInputStream();
            while(inputStream.read()!=-1){
                stringBuilder.append(b);
            }
            Analysis analysis=new Analysis(stringBuilder.toString(),tree);
            analysis.parse();
        }
    }

    public static void main(String[] args) {
        try {
            new Server();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}