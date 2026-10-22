import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

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

        lst.sort((u1,u2)-> u1.getEmail().compareTo(u2.getEmail()));
        for(User user : lst){
            System.out.println(user.getEmail() + " " + user.getPass());
        }
    }

}



