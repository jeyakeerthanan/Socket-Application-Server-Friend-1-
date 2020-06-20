package com.company;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;

public class Main {

    public static void main(String[] args) {
        Crypto crypto=new BasicCrypto();
        try{



            ServerSocket ss=new ServerSocket(1201);
            System.out.println("Waiting For Friend ");
            Socket s=ss.accept();
            System.out.println("Friend Connected");

            DataInputStream din=new DataInputStream(s.getInputStream());
            DataOutputStream dout=new DataOutputStream(s.getOutputStream());
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

            String msgin="", msgout="";
            while(!msgin.equalsIgnoreCase("end")){
                msgin = din.readUTF();
                String dec=new String(crypto.decrypt(msgin.getBytes())); //decrypting
                System.out.println("Friend:" + dec);// print chat_client message
                msgout = br.readLine();
                String enc=new String(crypto.encrypt(msgout.getBytes())); //encrypting

                System.out.println("Encrypted :" + enc);
                dout.writeUTF(enc);
                dout.flush();

            }

            s.close();
        }catch (Exception e){
            //Handle Exceptions
        }
    }
}

