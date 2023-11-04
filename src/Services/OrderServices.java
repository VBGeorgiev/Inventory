package Services;

import Database.Database;
import Model.Order;
import Model.User;

import java.io.File;
import java.util.HashMap;

public class OrderServices {
//    HashMap<orderReference, Order>
    private User user;
    private HashMap<String, Order> orderList;

    public OrderServices(User user) {
        this.user = user;
        this.orderList = new HashMap<>();
    }

    public OrderServices(User user, HashMap<String, Order> orderList) {
        this.user = user;
        this.orderList = orderList;
    }

    public HashMap<String, Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(HashMap<String, Order> orderList) {
        this.orderList = orderList;
    }

    public void addOrder(Order order) {
        this.orderList.put(order.getOrderReference(), order);
    }

    public void uploadOrderList(Database database, User user) {
        String location = database.getPath();
        this.orderList = new HashMap<>();
        File file = new File(location);
        if(!file.exists()) {
            this.addDemoOrder(user);
            System.out.println("Order database created with a demo item");
            database.saveObject(this.orderList);
        }
    }

    private void addDemoOrder(User user) {
        Order demoOrder = new Order(user, "demo order");
        this.orderList.put(demoOrder.getOrderReference(), demoOrder);
    }
}
