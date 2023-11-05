package Utility;

import Model.InventoryItem;

import java.util.HashMap;
import java.util.Scanner;

// parseNum method validates that user input for type int and double is correct
public class Utility {
    public static <T extends Number> T parseNum(T outType, String string, String userMessage, Scanner sc) {
        Number num = 0;
        boolean isInt = false;
        while(!isInt) {
            try{
                if(outType instanceof Integer) {
                    num = Integer.parseInt(string);
                    isInt = true;
                } else if (outType instanceof Double) {
                    num = Double.parseDouble(string);
                    isInt = true;
                } else {
                    throw new NumberFormatException("Wrong input");
                }

            } catch (NumberFormatException e) {
                System.out.println(userMessage);
                string = sc.nextLine();
            }

        }

        return (T) num;

    }

//    selectItemById validate that user id is present in the itemList,
//    if yes, returns the id but if not, returns -1
    public static int selectItemById(Scanner sc, HashMap<Integer, InventoryItem> itemList) {
        System.out.println("Please select item id: ");
        int id = Utility.parseNum(1, sc.nextLine(),
                "Please select an integer for item id", sc);
        if(!itemList.containsKey(id)) {
            System.out.println("Selected item id not found");
            return -1;
        } else {
            return id;
        }
    }

}
