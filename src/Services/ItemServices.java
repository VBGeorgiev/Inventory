package Services;

import Database.Database;
import Model.GroceryItem;
import Model.InventoryItem;

import java.io.File;
import java.util.HashMap;

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
        GroceryItem apple = new GroceryItem(1,5, "Demo apple", true, "fruit");
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
}
