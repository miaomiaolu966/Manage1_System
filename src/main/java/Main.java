import model.User;
import util.DataStore;
import view.LoginForm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static util.DataStore.DB_DIR;
import static util.DataStore.loadListFromFile;

public class Main {
    private final static Map<String, List<User>> dbData1= new HashMap<>();
    public static void main(String[] args) {
        //List<User> userList= ;
//        dbData1.put("User",loadListFromFile("User.json", User.class,DB_DIR));

        //DataStore.dbData
//        System.out.println(dbData1);
        new LoginForm().setLocation(850,400);
    }
}