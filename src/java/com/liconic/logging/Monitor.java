/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.liconic.logging;

import com.sun.javafx.Logging;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

/**
 *
 * @author Rucha Deshpande
 */
public class Monitor {
    private static Monitor instance;   
    static Monitor getInstance() {
        if(instance == null)
            instance = new Monitor();
        return instance;
    }
    
     public void RunMonitor(){
         
           Calendar cal = Calendar.getInstance();        
        try{
            Timer timer = new Timer();
            Loging log = new Loging(this);
            timer.schedule(log, cal.getTime(),1000*600);
        }
        catch(Exception e){
            System.out.println("Monitor timer error: " + e.getMessage());
        }
        
     }
     
     public String sendRequest(String id) {
         try {
            int port = 3333;
            String hostname = "localhost";
            String input,output;
            Socket socket = new Socket(hostname, port);           /*Connects to server*/
            String request = id;
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(request);       
            BufferedReader in = new BufferedReader(new
            InputStreamReader(socket.getInputStream()));  
            String response = in.readLine();/*Reads from server*/
            
            
            out.close();    /*Closes all*/
            in.close();
            socket.close();
            return response;
      }

        catch(Exception e) {
         System.out.print("Error Connecting to Server\n");
         return "Error Connecting to Server to get instrument status";
      } 
    }
     
     
}
