package com.javarush.task.task18.task1826;

/* 
Шифровка
*/

import java.io.*;

public class Solution {

    public static void main(String[] args) throws IOException {

        String sourceFileName = args[1];
        String targetFileName = args[2];

        if ((args[0].equals("-e")) || (args[0].equals("-d"))){
            byte[] data = readFromFile(sourceFileName); //read source file
            byte[] encData = processData(data);  //encrypt data from source
            writeToFile(targetFileName, encData); //write encrypted data to separate file
        }
        else System.out.println("Wrong Argument, use -e for encrypt or -d for decrypt");
        if(args[1] == null || args[2] == null) System.out.println("source or target files arguments not present ");
    }

    public static byte[] processData(byte[] data){

        String key = "javarush"; // key for XOR

        byte[] encData = new byte[data.length];
        byte[] encKey = key.getBytes();

        for(int i = 0; i < encData.length; i++){
            encData[i] = (byte) (data[i] ^ encKey[i % encKey.length]); // XOR encryption
        }
        return encData;
    }
    public static void writeToFile(String fileName, byte[] data) throws IOException{


        File file = new File(fileName);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(data);
        fileOutputStream.close();
    }
    public static byte[] readFromFile(String fileName) throws IOException{

        FileInputStream fileInputStream = new FileInputStream(fileName);
        byte[] data = new byte[fileInputStream.available()];

        for(int i = 0; i < data.length; i++){
            data[i] = (byte) fileInputStream.read();
        }
        fileInputStream.close();

        return data;
    }

}


