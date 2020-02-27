package com.javarush.task.task18.task1819;

/* 
Объединение файлов
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        FileInputStream source = new FileInputStream(reader.readLine());

        FileOutputStream destination = new FileOutputStream(reader.readLine(), true);



        byte[] buffer = new byte[1024];

        while (source.available() > 0){
            int count = source.read(buffer);
            destination.write(buffer, 0, count);
        }



        destination.close();
        source.close();

    }
}
