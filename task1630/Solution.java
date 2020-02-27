package com.javarush.task.task16.task1630;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Solution {
    public static String firstFileName;
    public static String secondFileName;

    //add your code here - добавьте код тут



    static {

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        try {
            firstFileName = input.readLine();
            secondFileName = input.readLine();
        }
        catch (IOException io){
            io.printStackTrace();
        }

//        firstFileName = "R:\\file1.txt";
//        secondFileName = "R:\\file2.txt";

    }

    public static void main(String[] args) throws InterruptedException {
        systemOutPrintln(firstFileName);
        systemOutPrintln(secondFileName);
    }

    public static void systemOutPrintln(String fileName) throws InterruptedException {
        ReadFileInterface f = new ReadFileThread();
        f.setFileName(fileName);
        f.start();
        f.join();
        //add your code here - добавьте код тут
        try {
            System.out.println(f.getFileContent());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public interface ReadFileInterface {

        void setFileName(String fullFileName);

        String getFileContent() throws IOException;

        void join() throws InterruptedException;

        void start();
    }

    //add your code here - добавьте код тут

    public static class ReadFileThread extends Thread implements ReadFileInterface{

        String filename;
        String content = "";

        @Override
        public void setFileName(String fullFileName) {
            filename = fullFileName;
        }

        @Override
        public String getFileContent() {

            return content;
        }

        @Override
        public void run() {

            try {

                BufferedReader reader = new BufferedReader(new FileReader(filename));
                StringBuilder builder = new StringBuilder();
                String currentLine = reader.readLine();
                while (currentLine != null) {
                    builder.append(currentLine);
                    builder.append(" ");
                    currentLine = reader.readLine();
                }


                reader.close();

                content = builder.toString();
            }
            catch (IOException e){
                e.printStackTrace();
            }


        }
    }



}
