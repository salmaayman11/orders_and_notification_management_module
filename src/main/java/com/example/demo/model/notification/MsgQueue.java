package com.example.demo.model.notification;
import java.util.Queue;
import java.util.LinkedList;
public class MsgQueue {
    private Queue<Notification> msgs = new LinkedList<>();

    public void add(Notofication noti) {
        msgs.add(noti);
    }
    public Queue<Notification> getAll() {
        for (Queue<Notification> notification : msgs) {
            return notification;
        }
        return null;
    }
    public Notification pop() {
        if (!notificationQueue.isEmpty()) {
            return notificationQueue.pop();
        }
        return null;
    }
}
