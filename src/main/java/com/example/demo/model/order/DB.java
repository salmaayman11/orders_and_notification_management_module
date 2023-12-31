package com.example.demo.model.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
public class DB {
    Map<String,Entity> list;
    public DB(){
        this.list= new HashMap<>();
    }
    public ArrayList<Entity> getAll(){
        return new ArrayList<>(list.values());
    }
    public boolean add(Entity entity){
        if(list.containsKey(entity.getKey())){
            return false;
        }
        else{
            list.put(entity.getKey(), entity);
            return true;
        }

    }
    public boolean delete(String key){
        if(list.containsKey(key)){
            list.remove(key);
            return true;
        }
        else{
            return false;
        }
    }
    public Entity get(String key){
        return list.getOrDefault(key, null);
    }
}

