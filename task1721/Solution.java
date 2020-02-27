package com.javarush.task.task17.task1721;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* 
Транзакционность
*/

public class Solution {
    public static List<String> allLines = new ArrayList<String>();
    public static List<String> forRemoveLines = new ArrayList<String>();

    public static void main(String[] args) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Solution join = new Solution();


        try{
            String filename1 = reader.readLine();
            String filename2 = reader.readLine();

            FileReader file1 = new FileReader(filename1);
            FileReader file2 = new FileReader(filename2);

            addAllLines(file1);
            addForRemoveLines(file2);

        }
        catch (IOException e){
            e.printStackTrace();
        }

        try{
            join.joinData();
        }
        catch (CorruptedDataException e){
            System.out.println("Corrupted Data!");
        }

    }

    public static synchronized void addAllLines(FileReader file){

        BufferedReader reader = new BufferedReader(file);

        try {
            String line = reader.readLine();

            while(line != null){
                allLines.add(line);
                line = reader.readLine();
            }

            reader.close();
        }

        catch (IOException e){
            e.printStackTrace();
        }



    }

    public static synchronized void addForRemoveLines(FileReader file){

        BufferedReader reader = new BufferedReader(file);

        try {
            String line = reader.readLine();

            while (line != null){
                forRemoveLines.add(line);
                line = reader.readLine();
            }

            reader.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

    public void joinData() throws CorruptedDataException {

        for (String remove : forRemoveLines)
            if (!allLines.remove(remove)) {
                allLines.clear();
                throw new CorruptedDataException();
            }

    }



}  //END OF SOLUTION

/*

очищаем только от тех строк что есть во втором файле.
Первый файл может содержать большее количество строк.
А вот если во втором файле есть строки которых нет в первом, тогда весь файл...
 список allLines попадает под замес


/Users/igor/file1
/Users/igor/file2
/Users/igor/file3


 */