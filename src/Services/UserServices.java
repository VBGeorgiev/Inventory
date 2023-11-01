package Services;

import Database.Database;
import Model.InventoryItem;
import Model.User;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

public class UserServices {
//    List of users as: HashMap<username, User>
    private HashMap<String, User> userList;

    public UserServices(HashMap<String, User> userList) {
        this.userList = userList;
    }

    public UserServices(){
        this.userList = new HashMap<String, User>();
    }

    public HashMap<String, User> getUserList() {
        return userList;
    }

    public void setUserList(HashMap<String, User> userList) {
        this.userList = userList;
    }

    public void add(User user) {
        this.userList.put(user.getUsername(), user);
    }

    public boolean register(Scanner sc) {
        System.out.println("Please enter your username: ");
        String username = sc.nextLine();
        if(this.userList.containsKey(username)) {
            System.out.println("Username already exists, please try again");
            return false;
        }
        System.out.println("Please enter your name: ");
        String name = sc.nextLine();
        System.out.println("Please enter your address: ");
        String address = sc.nextLine();
        System.out.println("Please enter your phone number: ");
        String phoneNumber = sc.nextLine();
        System.out.println("Please enter your password: ");
        String password = sc.nextLine();
        User newUser = new User(username, name, address, phoneNumber, password);
        this.add(newUser);
        System.out.println("User registration successful");
        return true;

    }

    public void uploadUserList(Database database) {
        String location = database.getPath();
        HashMap<Integer, InventoryItem> userList = new HashMap<>();
        File file = new File(location);
        if(!file.exists()) {
            this.addAdmin();
            System.out.println("Database created with admin user");
            database.saveObject(this.userList);
        }
    }

    private void addAdmin() {
        User admin = new User("admin", "admin", "World Wide Web", "0359666777", "adminPass");
        this.userList.put("admin", admin);
    }
}
