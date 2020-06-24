package com.company;

import java.io.*;
import java.net.*;


public class Main
{

    public static void main(String[] args) {
        Crypto crypto=new BasicCrypto();
        try{



            ServerSocket ss=new ServerSocket(1001);
            System.out.println("Waiting For Friend ");
            Socket s=ss.accept();
            System.out.println("Friend Connected");

            DataInputStream d_in=new DataInputStream(s.getInputStream());
            DataOutputStream d_out=new DataOutputStream(s.getOutputStream());
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

            String in, out;
            do{
                in = d_in.readUTF();
                String dec=new String(crypto.decrypt(in.getBytes())); //decrypting
                System.out.println("Friend:" + dec);// print chat_client message
                out = br.readLine();
                String enc=new String(crypto.encrypt(out.getBytes())); //encrypting

                System.out.println("Encrypted :" + enc);
                d_out.writeUTF(enc);
                d_out.flush();
               // System.out.println("in value is "+in);
            }while (!out.equals("bye"));
            System.out.println("Server closed");
            s.close();
        }catch (Exception e){
            //Handle Exceptions
        }
    }
}


