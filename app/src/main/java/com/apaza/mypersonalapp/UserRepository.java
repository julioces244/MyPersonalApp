package com.apaza.mypersonalapp;

import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aldair on 11/10/2017.
 */

public class UserRepository {

  /*  public static List<User> users = new ArrayList<>();

    static{
        users.add(new User("julio", "tecsup", "Julio Apaza"));
        users.add(new User("paolo", "tecsup", "Paolo Guerrero"));
        users.add(new User("andre", "tecsup", "André Carrillo"));
    }

    public static User login(String username, String password){
        for (User user : users){
            if(user.getUsusario().equalsIgnoreCase(username) && user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
    }

    public static User getUser(String username){
        for (User user : users){
            if(user.getUsusario().equalsIgnoreCase(username)){
                return user;
            }
        }
        return null;
    } */


    public static List<User> list(){
        List<User> users = SugarRecord.listAll(User.class);
        return users;
    }

    public static User read(Long id){
        User user = SugarRecord.findById(User.class, id);
        return user;
    }

    public static void create(String fullname, String pass, String nombres){
        User user = new User(fullname, pass, nombres);
        SugarRecord.save(user);
    }


    public static void update(String fullname, String pass, String nombres, Long id){
        User user = SugarRecord.findById(User.class, id);
        user.setNombres(fullname);
        user.setUsusario(pass);
        user.setPassword(nombres);
        SugarRecord.save(user);
    }

    public static void delete(Long id){
        User user = SugarRecord.findById(User.class, id);
        SugarRecord.delete(user);
    }




}
