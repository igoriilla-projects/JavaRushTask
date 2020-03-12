package com.javarush.task.task19.task1919;

/* 
Считаем зарплаты
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class Solution {

    public static void main(String[] args) throws IOException {
        Map<String, Double> names;

        if(args.length != 0){
            String filename = args[0];
            names = readFile(filename);
            names.forEach((k,v)-> System.out.println(k+" "+v));
        }
        else{
            System.out.println("no filename in argument");
        }
    }


    public static Map<String, Double> readFile(String filename) throws IOException {

        FileReader fileReader = new FileReader(filename);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        Map<String, Double> names = new TreeMap<>();

        while(bufferedReader.ready()){

            String[] line = bufferedReader.readLine().split(" ");
            String key = line[0];
            double value = Double.parseDouble(line[1]);
            names.merge(key,value, Double::sum); //Lambda for this -> names.merge(key,value, (oldVal, newVal)-> oldVal + newVal);
        }

        fileReader.close();
        return names;
    }

}
