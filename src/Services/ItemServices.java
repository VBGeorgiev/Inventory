package Services;

import Database.Database;
import Model.ElectronicsItem;
import Model.FragileItem;
import Model.GroceryItem;
import Model.InventoryItem;
import Utility.Utility;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

public class ItemServices {
    private HashMap<Integer, InventoryItem> itemList;

    public ItemServices(HashMap<Integer, InventoryItem> itemList) {
        this.itemList = itemList;
    }

    public ItemServices() {
        this.itemList = new HashMap<Integer, InventoryItem>();
    }

    public HashMap<Integer, InventoryItem> getItemList() {
        return itemList;
    }

    public void setItemList(HashMap<Integer, InventoryItem> itemList) {
        this.itemList = itemList;
    }

    public void addDemoItem() {
        GroceryItem apple = new GroceryItem(1,5, "Demo apple", "Initializing item database", true, "fruit");
        this.itemList.put(1, apple);
    }

    public void uploadItemList(Database database) {
        String location = database.getPath();
        HashMap<Integer, InventoryItem> itemList = new HashMap<>();
        File file = new File(location);
        if(!file.exists()) {
            this.addDemoItem();
            System.out.println("Database created with an initial demo item");
            database.saveObject(this.itemList);
        }
    }

    public void viewAll() {
        this.itemList
                .keySet()
                .stream()
                .forEach(key -> this.itemList.get(key).view());
    }

    public void add(Scanner sc) {
        int nextId = this.itemList.size() + 1;
        System.out.println("Please specify item details(or name): ");
        String details = sc.nextLine();
        System.out.println("Please give item description: ");
        String description = sc.nextLine();
        System.out.println("Please specify item quantity (a number): ");
        int quantity = Utility.parseNum(1, sc.nextLine(),
                "Please specify again item quantity (a number): ", sc);
        System.out.println("Please select item group:");
        System.out.println("Electronics: select 1");
        System.out.println("Fragile: select 2");
        System.out.println("Grocery: select 3");
        int itemGroup = Utility.parseNum(1, sc.nextLine(),
                "Please select item group:", sc);
        if(itemGroup == 1) {
            System.out.println("Please specify item voltage: ");
            int voltage = Utility.parseNum(1, sc.nextLine(),
                    "Please specify item voltage again: ", sc);
            System.out.println("Please specify item category: ");
            String category = sc.nextLine();
            ElectronicsItem electronicsItem = new ElectronicsItem(nextId, quantity, details, description, voltage, category);
            this.itemList.put(nextId, electronicsItem);
            System.out.println("Successful addition");
        } else if (itemGroup == 2) {
            System.out.println("Please specify item material: ");
            String material = sc.nextLine();
            System.out.println("Please specify item weight: ");
            double weight = Utility.parseNum(1.0, sc.nextLine(),
                    "Please specify item voltage again: ", sc);
            FragileItem fragileItem = new FragileItem(nextId, quantity, details, description, material, weight);
            this.itemList.put(nextId, fragileItem);
            System.out.println("Successful addition");
        } else if(itemGroup == 3) {
            System.out.println("Please specify if item is fresh (true/false): ");
            boolean isFresh = Boolean.parseBoolean(sc.nextLine());
            System.out.println("Please specify item category: ");
            String category = sc.nextLine();
            GroceryItem groceryItem = new GroceryItem(nextId, quantity, details, description, isFresh, category);
            this.itemList.put(nextId, groceryItem);
            System.out.println("Successful addition");
        }

    }

}
