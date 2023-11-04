package Services;

import Database.Database;
import Model.InventoryItem;
import Model.Order;
import Model.User;
import Utility.Utility;

import java.io.File;
import java.util.ArrayList;
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

    public boolean logIn(Scanner sc, ItemServices itemServices) {
        System.out.println("Please enter your username: ");
        String username = sc.nextLine();
        if(!this.userList.containsKey(username)) {
            System.out.println("Username not found, please try again");
            return false;
        }

        System.out.println("Please enter your password: ");
        String password = sc.nextLine();
        if(this.userList.get(username).getPassword().equals(password)) {
            ArrayList<String> userMenu = new ArrayList<>();
            System.out.println("======= Welcome to user menu =======");
            userMenu.add("Add an item: select 1");
            userMenu.add("View list of all items: select 2");
            userMenu.add("Search item by category: select 3");
            userMenu.add("Search item by id: select 4");
            userMenu.add("Place an order: select 5");
            userMenu.add("Exit: select 0");
            int userChoice = 99;
            while(userChoice != 0 ) {
                System.out.println("==========================================");
                userMenu.forEach(System.out::println);
                System.out.println("==========================================");
                try {
                    userChoice = Integer.parseInt(sc.nextLine());
                    switch (userChoice) {
                        case 1:
                            itemServices.add(sc);
                            break;
                        case 2:
                            itemServices.viewAll();
                            break;
                        case 3:
                            System.out.println("Work in progress 3");
                            break;
                        case 4:
                            System.out.println("Work in progress 4");
                            break;
                        case 5:
                            if(!this.placeOrderById(sc, username, itemServices)) {
                                System.out.println("Place order not successful");
                            } else {
                                System.out.println("Place order successful");
                            }
                            break;
                        case 0:
                            break;
                        default:
                            userChoice = 99;
                            System.out.println("Please try again selecting from menu options only");
                    }
                } catch (Exception e) {
                    System.out.println(e);
                    System.out.println("Please try again");
                    userChoice = 99;
                }

            }

            return true;
        } else {
            return false;
        }

    }

    public boolean placeOrderById(Scanner sc, String username, ItemServices itemServices) {
        int itemId = Utility.selectItemById(sc, itemServices.getItemList());
        if(itemId == -1) {
            return false;
        }

        InventoryItem selectedItem = itemServices.getItemList().get(itemId);
        System.out.println("Please select item quantity: ");
        int quantity = Utility.parseNum(1, sc.nextLine(),
                "Please enter an integer for item quantity", sc);
        int currentItemQuantity = selectedItem.getQuantity();
        if(quantity <= currentItemQuantity) {
            int itemQuantityLeft = currentItemQuantity - quantity;
            selectedItem.setQuantity(itemQuantityLeft);
            ArrayList<InventoryItem> newOrderedItems = new ArrayList<>();
            newOrderedItems.add(selectedItem);
            Order newOrder = new Order(this.userList.get(username), "New purchase");
            newOrder.setOrderedItems(newOrderedItems);
            return true;
        } else {
            System.out.println("Required item quantity not available");
            return false;
        }

    }

    public void uploadUserList(Database database) {
        String location = database.getPath();
        HashMap<Integer, InventoryItem> userList = new HashMap<>();
        File file = new File(location);
        if(!file.exists()) {
            this.addAdmin();
            System.out.println("User database created with admin user");
            database.saveObject(this.userList);
        }
    }

    private void addAdmin() {
        User admin = new User("admin", "admin", "World Wide Web", "0359666777", "adminPass");
        this.userList.put("admin", admin);
    }
}
