package Services;

import Constants.Constants;
import Database.Database;
import Model.InventoryItem;
import Model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class OperationServices {
    public static void run() {
        String itemListLocation = Constants.databaseLocation + "itemList.txt";
        String userListLocation = Constants.databaseLocation + "userList.txt";
        Database itemDatabase = new Database(itemListLocation);
        Database userDatabase = new Database(userListLocation);
//        Uploading item and user database
        ItemServices itemServices = new ItemServices();
        itemServices.uploadItemList(itemDatabase);
        itemServices.setItemList((HashMap<Integer, InventoryItem>) itemDatabase.readObject());
        UserServices userServices = new UserServices();
        userServices.uploadUserList(userDatabase);
        userServices.setUserList((HashMap<String, User>) userDatabase.readObject());
//        User Interface
        Scanner sc = new Scanner(System.in);
        ArrayList<String> guestMenu = new ArrayList<>();
        System.out.println("======= Welcome to e-commerce shop =======");
        System.out.println("For purchasing items you have to log in or register.");
        guestMenu.add("Add an item: select 1");
        guestMenu.add("View list of all items: select 2");
        guestMenu.add("Search item by category: select 3");
        guestMenu.add("Search item by id: select 4");
        guestMenu.add("Log in user: select 5");
        guestMenu.add("Register user: select 6");
        guestMenu.add("Admin services (authorization required): select 7");
        guestMenu.add("Exit: select 0");
        int userChoice = 99;
        while(userChoice != 0 ) {
            System.out.println("==========================================");
            guestMenu.forEach(System.out::println);
            System.out.println("==========================================");
            try {
                userChoice = Integer.parseInt(sc.nextLine());
                switch (userChoice) {
                    case 1:
                        itemServices.add(sc);
                        itemDatabase.saveObject(itemServices.getItemList());
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
                        System.out.println("Work in progress 5");
                        break;
                    case 6:
                        System.out.println("Work in progress 6");
                        break;
                    case 7:
                        System.out.println("Work in progress 7");
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

    }

}
