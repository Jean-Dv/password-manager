package com.password_manager.controller;

import org.json.JSONArray;
import org.json.JSONObject;

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
        String hashPassword = encryptService.encryptPassword(password).getResult();
        String salt = encryptService.encryptPassword(password).getSalt();
        JSONArray users = this.getUsers();
        JSONObject newUser = new JSONObject();
        newUser.put("username", username);
        newUser.put("salt", salt);
        newUser.put("hash", hashPassword);
        users.put(newUser);
        this.jsonToObjectService.saveObject("users", users);
    }
}
