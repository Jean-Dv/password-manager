package com.password_manager.controller;

import org.json.JSONArray;
import org.json.JSONObject;

import com.password4j.Hash;
import com.password_manager.controller.interfaces.UserController;
import com.password_manager.services.EncryptServiceImplement;
import com.password_manager.services.JsonToObjectServiceImplement;

public class UserControllerImplement implements UserController {
    JsonToObjectServiceImplement jsonToObjectService = JsonToObjectServiceImplement.getInstance("./data.json");

    @Override
    public JSONArray getUsers() {
        JSONObject obj = this.jsonToObjectService.getObject();
        JSONArray users = obj.getJSONArray("users");
        return users;
    }

    @Override
    public void saveUser(String username, String password) {
        EncryptServiceImplement encryptService = new EncryptServiceImplement();
        Hash hash = encryptService.encryptPassword(password);
        String hashPassword = hash.getResult();
        String salt = hash.getSalt();
        JSONArray users = this.getUsers();
        JSONObject newUser = new JSONObject();
        newUser.put("username", username);
        newUser.put("salt", salt);
        newUser.put("hash", hashPassword);
        users.put(newUser);
        this.jsonToObjectService.saveObject("users", users);
    }

    @Override
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

    @Override
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

    @Override
    public boolean signIn(String username, String password) {
        String hashPassword = this.getHashPassword(username);
        String salt = this.getSalt(username);
        EncryptServiceImplement encryptService = new EncryptServiceImplement();
        return encryptService.verifyPassword(password, hashPassword, salt);
    }
}
