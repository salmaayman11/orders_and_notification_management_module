package com.example.demo.service;
import com.example.demo.model.SimpleRequestOrder;
import com.example.demo.model.order.*;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.example.demo.model.notification.*;


import java.util.*;

@Service
public class OrderService {

    MsgQueue queue = new MsgQueue();
    Notification noti;
    OrderDB orders = new OrderDB();
    DB products = new DB();
    DB customers = new DB();
    OrderService() {
        customers.add(new Customer("125", "Tony", 51000.0, "antoy.safaga@gmail.com", "01270856065"));
        customers.add(new Customer("126", "Ahmed", 5100.0, "amhed.safaga@gmail.com", "01270856060"));
        products.add(new Product("Yoghurt", "25", 12.5, "El Maraie", "Milks"));
        products.add(new Product("Milk", "12", 60, "El Maraie", "Milks"));
        products.add(new Product("Cheese", "0", 8.4, "El Maraie", "Milks"));
        products.add(new Product("Ahmed Tea", "205", 150.25, "Ahmed Tea", "Beverages"));
        products.add(new Product("Pillow", "69", 50, "The Oriental Weavers", "Cottons"));
    }

  public boolean placeSimpleOrder(String customerId, String location, List<String> prodsId) throws InterruptedException {
        SimpleOrder temp = createSimpleOrder(customerId, location, prodsId);
        if(temp == null) return false;
        temp.getCustomer().setAmount(temp.getCustomer().getAmount() - temp.cost());
        if(!orders.add(temp)) return false;
        orderPlacementNotification(temp.getCustomer(), temp);
        return true;
    }
    public boolean placeCompoundOrder(List<SimpleRequestOrder> list) throws InterruptedException {
        CompoundOrder order = new CompoundOrder();
        for (SimpleRequestOrder order_req:
             list) {
            SimpleOrder temp = createSimpleOrder(order_req.customerId, order_req.location, order_req.productsId);
            if(temp == null) {
                System.out.println("order cannot be created");
                return false;
            }
            if(!order.add(temp)) {
                System.out.println("order cannot be added");
                return false;
            }
            temp.getCustomer().setAmount(temp.getCustomer().getAmount() - temp.cost());
            orderPlacementNotification(temp.getCustomer(), temp);
        }
        return orders.add(order);
    }
    public Customer getCustomer(String key) {
        return (Customer) customers.get(key);
    }
    private SimpleOrder createSimpleOrder(String customerId, String location, List<String> prodsId) {
        ArrayList<Product> prods = new ArrayList<>();
        for (String id :
                prodsId) {
            Product p = (Product) products.get(id);
            if(p == null) {
                System.out.println("No Such products");
                return null;
            }
            prods.add(p);
        }
        Customer cus = (Customer) (customers.get(customerId));
        if(cus == null) {
            System.out.println("No such customer " + customerId);
            return null;
        }
        return new SimpleOrder(location, cus, prods);
    }
    public String getAllOrders() {
        String s = "";
        int i = 0;
        for (Order o :
                orders.getAll()) {
            i++;
            s += "---------------\n";
            s += "Order " + i + "\n";
            s += o.details() + "\n---------------";
        }
        return s;
    }
    @Async
    public void deleteMessage() throws InterruptedException {
        queue.pop();
        Thread.sleep(30000);
    }

    public boolean shipSimpleOrder(String orderKey) throws InterruptedException {
        Order o = (SimpleOrder) orders.get(orderKey);
        if (o != null) {
            Customer c = ((SimpleOrder) o).getCustomer();
            c.setAmount(c.getAmount() - o.fees());
            shippmentNotification(c, o);
            return true;
        }
        return false;
    }
    public boolean shipCompoundOrder(String orderKey) throws InterruptedException {
        Order o = (CompoundOrder) orders.get(orderKey);
        ArrayList<Order> os = ((CompoundOrder) o).getOrders();
        ArrayList<Customer> cs = new ArrayList<>();
        if (o != null) {
            for (Order or : os) {
                Customer c = ((SimpleOrder) or).getCustomer();
                c.setAmount(c.getAmount() - o.fees()/os.size());
            }
            for (Order or : os) {
                Customer c = ((SimpleOrder) or).getCustomer();
                shippmentNotification(c, or);
            }
            return true;
        }
        return false;
    }
    public ArrayList<Entity> allProducts() {
        return products.getAll();
    }

    public Map<String,Integer> countRemainingPartsByCategories(){
        //ArrayList<Entity> productsList = products.getAll();
        ArrayList<Entity> productsList = allProducts();
        Map<String, Integer> categoryRemainingParts = new HashMap<>();

        for(Entity entity: productsList){
            if(entity instanceof Product){
                Product product = (Product) entity;
                String category = product.getCategory();
                int remainingParts = categoryRemainingParts.getOrDefault(category,0);
                remainingParts ++;
                categoryRemainingParts.put(category,remainingParts);
            }
        }
        return categoryRemainingParts;
    }

    public String createAccount(Customer customer){
        // Random customerKey = new Random();
        String customKey = UUID.randomUUID().toString();
        customer.setKey(customKey);
        if(!customers.add(customer)) return "Account creation failed!";
        return customKey;
    }
    public Notification orderPlacementNotification(Customer customer, Order order) throws InterruptedException {
        Random rand = new Random();
        NotificationFactory channel;
        int num = rand.nextInt(2);
        if (num == 0) {
            channel = new SMSNotification(order, customer);
        } else {
            channel = new EmailNotification(order, customer);
        }
        Notification noti = channel.createPlacemenetNoti();
        if (queue.getAll().isEmpty()){
            queue.add(noti);
            deleteMessage();
        }
        else
            queue.add(noti);
        return noti;
    }
    public Notification shippmentNotification(Customer user, Order order) throws InterruptedException {
        Random rand = new Random();
        NotificationFactory channel;
        int num = rand.nextInt(2);
        if (num == 0) {
            channel = new SMSNotification(order, user);
        } else {
            channel = new EmailNotification(order, user);
        }
        Notification noti = channel.createShippmentNoti();
        if (queue.getAll().isEmpty()){
            queue.add(noti);
            deleteMessage();
        }
        else
            queue.add(noti);
        return noti;
    }
    public Queue<Notification> getQueueNotifications() {
        return queue.getAll();
    }
}
