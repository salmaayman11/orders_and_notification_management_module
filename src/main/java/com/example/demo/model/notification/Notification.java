package com.example.demo.model.notification;
import com.example.demo.model.order.*;

public class Notification {
    private String content ;

    private String language;
    private Customer reciepent;

    public Notification(String content, String language, Customer reciepent) {
        this.content = content;
        this.language = language;
        this.reciepent = reciepent;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
    public void display(){
        System.out.println("language "+this.language);
        System.out.println(this.content);

    }
}
