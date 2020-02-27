package com.javarush.task.task18.task1825;

import java.io.*;
import java.util.*;

/* 
Собираем файл
*/

public class Solution {
    public static void main(String[] args) throws IOException {

        ArrayList<String> fileNames = new ArrayList<>(); //change to set cuz filename must be unique

        //read filenames and add to list
        FileRead(fileNames);

        // добавляем в сет чтобы исключить повторы
        Set<String> files = new TreeSet<>(fileNames);
        // и возвращаяем обратно
        fileNames = new ArrayList<>(files);

        //add all parts in 1 file
        ConcatenateFiles(fileNames);
    }

    public static ArrayList<String> FileRead(ArrayList<String> filenames) throws IOException{

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while(true){

            try {
                String filename = reader.readLine();
                if (filename.equals("end")) {
                    break;
                }
                FileInputStream fileInputStream = new FileInputStream(filename);
                filenames.add(filename);
                fileInputStream.close();
            }
            catch (FileNotFoundException e){
                System.out.println("File not found!");
            }
        }

        return filenames;

    }

    public static void ConcatenateFiles(ArrayList<String> filenames) throws IOException {


        //get output filename from filename parts
        String[] temp = filenames.get(0).split("(.part)\\d");

        String filename = temp[0];
        //

        File outFile = new File(filename);
        FileOutputStream fileOutputStream = new FileOutputStream(outFile,true);

        for(int i = 0; i < filenames.size(); i++) {

            FileInputStream fileInputStream = new FileInputStream(filenames.get(i));

            byte[] buffer = new byte[fileInputStream.available()];

            while(fileInputStream.available() > 0){
                int count = fileInputStream.read(buffer);
                fileOutputStream.write(buffer, 0 ,count);
            }

            fileInputStream.close();

        }

        fileOutputStream.close();

    }
}
