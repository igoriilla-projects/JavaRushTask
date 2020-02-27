package com.javarush.task.task17.task1711;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* 
CRUD 2
*/

public class Solution {
    public static volatile List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) throws ParseException {
        //start here - начни тут

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

        switch (args[0]){

            case "-c": {
                synchronized (allPeople) {
                    for (int i =1;i<args.length;i = i+3) {
                        Date date = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(args[i+2]);
                        if (args[i +1].equals("м"))
                            allPeople.add(Person.createMale(args[i], date));
                        if (args[i+1].equals("ж"))
                            allPeople.add(Person.createFemale(args[i], date));
                        System.out.println(allPeople.size() - 1);

                    }
                }
                break;
            }

            case "-u": {
                synchronized (allPeople) {
                    for (int i = 1; i < args.length; i = i + 4) {
                        int id = Integer.parseInt(args[i]);
                        if (id >= 0 && id < allPeople.size()) {
                            Person person = allPeople.get(id);
                            person.setName(args[i+1]);
                            person.setSex(args[i+2].equals("м") ? Sex.MALE : Sex.FEMALE);
                            person.setBirthDate(new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(args[i+3]));
                        }
                    }
                }
                break;
            }


            case "-d": {
                synchronized (allPeople) {
                    for (int i = 1; i < args.length; i++) {
                        int id = Integer.parseInt(args[i]);
                        if (id >= 0 && id < allPeople.size()) {
                            Person person = allPeople.get(id);
                            person.setName(null);
                            person.setSex(null);
                            person.setBirthDate(null);
                        }
                    }
                }
                break;
            }
            case "-i": {
                synchronized (allPeople) {
                    for (int i = 1; i < args.length; i++) {
                        Person person = allPeople.get(Integer.parseInt(args[i]));
                        SimpleDateFormat sd = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
                        String sex = person.getSex().equals(Sex.FEMALE) ? "ж" : "м";
                        System.out.println(person.getName() + " " + sex + " " + sd.format(person.getBirthDate()));
                    }
                }
                break;
            }
        }

        }
}
