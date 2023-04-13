package com.password_manager.controller;

import org.json.JSONArray;
import org.json.JSONObject;

import com.password4j.Hash;
import com.password_manager.controller.interfaces.UserController;
import com.password_manager.services.EncryptServiceImplement;
import com.password_manager.services.JsonToObjectServiceImplement;
import com.password_manager.models.User;

public class UserControllerImplement implements UserController {
    JsonToObjectServiceImplement jsonToObjectService = JsonToObjectServiceImplement.getInstance("./db/users.json");

    @Override
    public JSONArray getUsers() {
        JSONArray users = this.jsonToObjectService.getList();
        return users;
    }

    @Override
    public void createUser(String username, String password) {
        EncryptServiceImplement encryptService = new EncryptServiceImplement();
        Hash hash = encryptService.encryptPassword(password);
        String hashPassword = hash.getResult();
        String salt = hash.getSalt();
        JSONArray users = this.getUsers();
        User newUser = new User(username, hashPassword, salt);
        users.put(newUser);
        this.jsonToObjectService.saveObject("users", users);
    }

    public String getHashPassword(String username) {
        JSONArray users = this.getUsers();
        for (int i = 0; i < users.length(); i++) {
            JSONObject user = users.getJSONObject(i);
            if (user.getString("username").equals(username)) {
                return user.getString("hash");
            }
        }
        return "User not found";
    }

    public String getSalt(String username) {
        JSONArray users = this.getUsers();
        for (int i = 0; i < users.length(); i++) {
            JSONObject user = users.getJSONObject(i);
            if (user.getString("username").equals(username)) {
                return user.getString("salt");
            }
        }
        return "User not found";
    }

    public boolean signIn(String username, String password) {
        String hashPassword = this.getHashPassword(username);
        String salt = this.getSalt(username);
        EncryptServiceImplement encryptService = new EncryptServiceImplement();
        return encryptService.verifyPassword(password, hashPassword, salt);
    }

    public void sortUsers() {
        JSONArray users = this.getUsers();
        for (int i = 0; i < users.length(); i++) {
            for (int j = i + 1; j < users.length(); j++) {
                JSONObject user1 = users.getJSONObject(i);
                JSONObject user2 = users.getJSONObject(j);
                if (user1.getString("username").compareTo(user2.getString("username")) > 0) {
                    users.put(i, user2);
                    users.put(j, user1);
                }
            }
        }
        this.jsonToObjectService.saveObject("users", users);
    }
}
