package com.example.demo.model.notification;
import java.util.Queue;
import java.util.LinkedList;
import java.util.concurrent.*;
public class MsgQueue {
    private Queue<Notification> msgs = new LinkedList<>();

    public void add(Notification noti) {
        msgs.add(noti);
    }
    public Queue<Notification> getAll() {
        return msgs;
    }
//    public void pop() {
//        if (!msgs.isEmpty()) {
//            ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
//            executorService.scheduleAtFixedRate(() -> {
//                try {
//                     msgs.poll();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }, 0, 30, TimeUnit.SECONDS);
//            executorService.shutdown();
//        }
//        }

    }

