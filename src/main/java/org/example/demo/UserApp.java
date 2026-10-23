package org.example.demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.io.*;
import java.io.IOException;

public class UserApp {
    public static void main( String[] args ) {
        ArrayList<User> lst = new ArrayList<>();
        File readFile = new File("Users.txt");
        Scanner reader;
        try {
            reader = new Scanner(readFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        while(reader.hasNextLine()){
            String data = reader.nextLine();
            String[] parts = data.trim().split("\\s+");
            try{
                User user = new User(parts[0], parts[1]);
                lst.add(user);
            } catch (IllegalArgumentException e){
                System.out.println(parts[0] + " " + parts[1] + " " + e.getMessage());
            }
        }

        lst.sort(Comparator.comparing(User::getEmail));
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("out.txt"))) {

            for (User user : lst) {
                writer.write(user.getEmail() + " " + user.getPass());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}



