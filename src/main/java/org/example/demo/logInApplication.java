package org.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class logInApplication extends Application {
    private ArrayList<User> users;

    @Override
    public void init() throws Exception {
        this.users = new ArrayList<>();
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
                users.add(user);
            } catch (IllegalArgumentException e){}
        }
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(logInApplication.class.getResource("login.fxml"));
        Parent root = fxmlLoader.load();
        logInController controller = fxmlLoader.getController();
        controller.setUsers(users);

        Scene scene = new Scene(root, 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}