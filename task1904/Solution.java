package com.javarush.task.task19.task1904;

/* 
И еще один адаптер
*/

import java.io.*;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class Solution {

    public static void main(String[] args) throws IOException, ParseException {

        Scanner sc = new Scanner(Paths.get("D:/files/person.txt"));
        PersonScanner psc = new PersonScannerAdapter(sc);

        System.out.println(psc.read());
        System.out.println(psc.read());
        psc.close();

    }
    public static class PersonScannerAdapter implements PersonScanner {

        private final Scanner fileScanner;

        public PersonScannerAdapter(Scanner fileScanner){
            this.fileScanner = fileScanner;
        }

        @Override
        public Person read() throws IOException, ParseException {

            SimpleDateFormat dateFormat = new SimpleDateFormat("d M y", Locale.ENGLISH);
            String person = fileScanner.nextLine();

            String[] split = person.split("\\s" ,4);
            Date birthDate =  dateFormat.parse(split[3]);

            return new Person(split[1], split[2], split[0], birthDate);
        }

        @Override
        public void close() throws IOException {
            fileScanner.close();
        }
    }
}
