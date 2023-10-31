package Utility;

import java.util.Scanner;

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

}
