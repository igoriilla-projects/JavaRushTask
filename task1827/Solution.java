package com.javarush.task.task18.task1827;

/* 
Прайсы
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String filename = reader.readLine();

        int id = 0;
        String productName = null;
        double price = 0.0;
        int quantity = 0;

        try {
            switch (args[0]) {

                case ("-c"):
                    id = findIDinFile(filename);
                    productName = args[1];
                    if(productName.length() > 30){
                        System.out.println("product name must be less than 30 symbols");
                    }

                    try {
                        price = Double.parseDouble(args[2]);
                    }
                    catch (NumberFormatException e){
                        System.out.println("Price must be in format 0.0");
                    }

                    quantity = Integer.parseInt(args[3]);
                    writeToFile(id, productName, price, quantity, filename);
                    break;
                default:
                    System.out.println("Usage: -c productName price quantity");
            }
        }
        catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Usage: -c id productName price quantity");
        }
    }

    public static int findIDinFile(String fileName) throws IOException {

        FileInputStream inputStream = new FileInputStream(fileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String line = reader.readLine();
        int lastID= 0;
        int max = Integer.MIN_VALUE;

        while (line !=null) {

            lastID = Integer.parseInt(line.substring(0, 8).replaceAll("\\s", ""));

            if(lastID > max){
                max = lastID;
            }

            line = reader.readLine();

        }

        inputStream.close();
        reader.close();

        return max;
    }

    public static void writeToFile(int id, String name, double price, int quantity, String fileName) throws IOException {


        FileOutputStream outputStream = new FileOutputStream(fileName, true);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));

        String writeID = String.valueOf(id+1); //increment last id
        String writePrice = String.valueOf(price);
        String writeQuantity = String.valueOf(quantity);

        if(writeID.length() < 8) writeID = addSpaceToString(writeID, 8);
        if(name.length() < 30) name = addSpaceToString(name, 30);
        if(writePrice.length() < 8) writePrice = addSpaceToString(writePrice, 8);
        if(writeQuantity.length() < 4) writeQuantity = addSpaceToString(writeQuantity, 4);

        String writeString = writeID+name+writePrice+writeQuantity;
        writer.write("\n"+writeString);

        writer.close();
        outputStream.close();

    }

    public static String addSpaceToString(String string, int spaces){
        return String.format("%-"+spaces+"s", string);
    }

}
