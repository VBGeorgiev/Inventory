package Services;

import Model.InventoryItem;
import Model.Order;
import Model.User;
import Utility.Utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class AdminServices {
    private HashMap<Integer, InventoryItem> itemList;
    private HashMap<String, User> userList;
    private HashMap<String, Order> orderList;

    public AdminServices(HashMap<Integer, InventoryItem> itemList,
                         HashMap<String, User> userList,
                         HashMap<String, Order> orderList) {
        this.itemList = itemList;
        this.userList = userList;
        this.orderList = orderList;
    }

    public boolean logAsAdmin(Scanner sc) {
        System.out.println("Please enter admin name: ");
        String username = sc.nextLine();
        if(!this.userList.containsKey(username) || !username.equals("admin")) {
            System.out.println("Admin name not found, please try again");
            return false;
        }

        System.out.println("Please enter admin password: ");
        String password = sc.nextLine();
        if(this.userList.get(username).getPassword().equals(password)) {
            ArrayList<String> adminMenu = new ArrayList<>();
            System.out.println("======= Welcome to administrator menu =======");
            adminMenu.add("Set an item ready for sale: select 1");
            adminMenu.add("View all purchase orders: select 2");
            adminMenu.add("Exit: select 0");
            int adminChoice = 99;
            while(adminChoice != 0 ) {
                System.out.println("==========================================");
                adminMenu.forEach(System.out::println);
                System.out.println("==========================================");
                try {
                    adminChoice = Integer.parseInt(sc.nextLine());
                    switch (adminChoice) {
                        case 1:
                            setItemForSellById(sc);
                            break;
                        case 2:
                            viewOrders();
                            break;
                        case 0:
                            break;
                        default:
                            adminChoice = 99;
                            System.out.println("Please try again selecting from menu options only");
                    }
                } catch (Exception e) {
                    System.out.println(e);
                    System.out.println("Please try again");
                    adminChoice = 99;
                }

            }

            return true;
        } else {
            System.out.println("Wrong password, please try again");
            return false;
        }

    }

    public boolean setItemForSellById(Scanner sc){
        System.out.println("Please select item id: ");
        int id = Utility.parseNum(1, sc.nextLine(),
                "Please select an integer for item id", sc);
        if(!this.itemList.containsKey(id)) {
            System.out.println("Selected item id not found");
            return false;
        }
        System.out.println("Please give a price for this " + this.itemList.get(id).getDetails() + ":");
        double price = Utility.parseNum(1.00, sc.nextLine(),
                "Please enter a double type value for item price", sc);
        this.itemList.get(id).setSellable(price);
        System.out.println("Item ready for sale");
        return true;
    }

    public void viewOrders(){
        this.orderList
                .keySet()
                .stream()
                .forEach(orderRef -> {
                    System.out.println("==========================================");
                    this.orderList.get(orderRef).view();
                });
    }

}
