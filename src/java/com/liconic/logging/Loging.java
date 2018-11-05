/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.liconic.logging;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;

/**
 *
 * @author Rucha Deshpande
 */
public class Loging extends TimerTask{
    Monitor monitor;
    String temp = "";
    String Co2 = "";
    String T44 = "";
    String RD1504 = "";
    String RD6500 = "";
    String RD1915 = "";
    String RD1811 = "";
    String RD1814 = "";
    String RD3906 = "";
  //  PrintStream out;
   BufferedWriter bw = null;
   FileWriter fw = null;
    
    public Loging(Monitor monitor) throws FileNotFoundException{
        try{ 
        File file = new File("C:\\Liconic\\log.txt");
            if(file.exists()){
             DateFormat dateFormat = new SimpleDateFormat("mm.dd.yyyy HH:mm:ss");
        Date date = new Date();
             file = new File("C:\\Liconic\\log"+date.getTime()+".txt");             
         } 
            fw = new FileWriter(file.getAbsolutePath());
            bw = new BufferedWriter(fw);
            bw.write("date;temp;Co2;T44;RD1504;RD6500;RD1915;RD1811;RD1814;RD3906");
         //out.println("date;temp;Co2;T44;RD1504;RD6500;RD1915;RD1811;RD1814;RD3906");
        }
        catch(Exception e){
            System.out.println("File Error " + e.getMessage());
        }
          this.monitor = monitor;
    }
      @Override
    public void run() {
     try{
        System.out.println("----------------------------------------------------------------------------------------------------------------------");
        temp = monitor.sendRequest("STX2ReadBackStr(UNIT1,RD DM890)");
        Co2 = monitor.sendRequest("STX2ReadBackStr(UNIT1,RD DM894)");
        T44 = monitor.sendRequest("STX2ReadBackStr(UNIT1,RD T44)");
        RD1504 = monitor.sendRequest("STX2ReadBackStr(UNIT1,RD 1504)");
        RD6500 = monitor.sendRequest("STX2ReadBackStr(UNIT1,RD 6500)");
        RD1915 = monitor.sendRequest("STX2ReadBackStr(UNIT1,RD 1915)");
        RD1811 = monitor.sendRequest("STX2ReadBackStr(UNIT1,RD 1811)");
        RD1814 = monitor.sendRequest("STX2ReadBackStr(STXPlateStore,RD 1814)");
        RD3906 = monitor.sendRequest("STX2ReadBackStr(UNIT1,RD 3906)");
        
        DateFormat dateFormat = new SimpleDateFormat("mm.dd.yyyy HH:mm:ss");
        Date date = new Date();
        System.out.println( date + " "+ temp + "  " + Co2 + "  " + T44 + "  " + RD1504 + "  " + RD6500 + "  " + RD1915 + "  " + RD1811 + "  " + RD1814 + " " + RD3906);
        
       bw.append(date  + ";"+ temp + ";" + Co2 + ";" + T44 + ";" + RD1504 + ";" + RD6500 + ";" + RD1915 + ";" + RD1811 + ";" + RD1814 + ";" + RD3906);
        
           System.out.println("----------------------------------------------------------------------------------------------------------------------");  
     }
     catch(Exception e){
        System.out.println("Exception file: " + e.getMessage());
     }
}
}
