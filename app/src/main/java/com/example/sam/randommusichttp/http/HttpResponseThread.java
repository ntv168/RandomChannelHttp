package com.example.sam.randommusichttp.http;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;


import com.example.sam.randommusichttp.MainActivity;
import com.example.sam.randommusichttp.MusicSingleton;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


/**
 * Created by Thuans on 4/19/2017.
 */

public class HttpResponseThread extends Thread {
    private final String TAG = "HttpResponseThread";
    Socket socket;
    Context context;

    HttpResponseThread(Socket socket, Context context){
        this.socket = socket;
        this.context = context;
    }

    @Override
    public void run() {
        BufferedReader is;
        PrintWriter os;
        String request = "";


        try {
            is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            request = is.readLine();
            MusicSingleton singleton = MusicSingleton.getInstance(context);
            Log.d(TAG,request+"");
            //kiem tra request url
            String response = "";
            os = new PrintWriter(socket.getOutputStream(), true);
            if (request != null ) {
                if (request.contains("/music")) {

                    singleton.playRandomSound();

                    response = "Music on";
                } else if (request.contains("/vtv")) {
                    singleton.playVTVchanel();
                    response = "VTV on";
                } else if (request.contains("/htv")) {
                    singleton.playHTVchanel();
                    response = "HTV on";
                }
            }
            os.print("HTTP/1.0 200" + "\r\n");
            os.print("Content type: text/html" + "\r\n");
            os.print("Content length: " + response.length() + "\r\n");
            os.print("\r\n");
            os.print(response + "\r\n");
            os.flush();
            socket.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return;
    }
}
