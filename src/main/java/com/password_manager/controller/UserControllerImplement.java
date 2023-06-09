package com.password_manager.controller;

import org.json.JSONArray;
import org.json.JSONObject;

import com.password4j.Hash;
import com.password_manager.controller.interfaces.UserController;
import com.password_manager.services.DirectoryServiceImplement;
import com.password_manager.services.EncryptServiceImplement;
import com.password_manager.services.JSONServiceImplement;
import com.password_manager.models.User;

public class UserControllerImplement implements UserController {

    public void createDirectoryPersonal(String username) {
        DirectoryServiceImplement directoryService = new DirectoryServiceImplement();
        directoryService.writeFile(username);
    }

    @Override
    public JSONArray getUsers() {
        JSONServiceImplement jsonToObjectService = new JSONServiceImplement("./db/users.json");
        JSONArray users = jsonToObjectService.getList();
        return users;
    }

    @Override
    public void createUser(String username, String password) {
        JSONServiceImplement jsonToObjectService = new JSONServiceImplement("./db/users.json");
        EncryptServiceImplement encryptService = new EncryptServiceImplement();
        Hash hash = encryptService.encryptPassword(password);
        String hashPassword = hash.getResult();
        String salt = hash.getSalt();
        JSONArray users = this.getUsers();
        User newUser = new User(username, hashPassword, salt);
        users.put(newUser);
        jsonToObjectService.saveObject(users);
        this.createDirectoryPersonal(username);
    }

    public boolean signIn(String username, String password) {
        User user = this.getUser(username);
        String hashPassword = user.getHash();
        String salt = user.getSalt();
        EncryptServiceImplement encryptService = new EncryptServiceImplement();
        return encryptService.verifyPassword(password, hashPassword, salt);
    }

    public void sortUsers() {
        JSONServiceImplement jsonToObjectService = new JSONServiceImplement("./db/users.json");
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
        jsonToObjectService.saveObject(users);
    }

    private int search(JSONArray arr, String x) {
        int left = 0;
        int right = arr.length() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int result = x.compareTo(arr.getJSONObject(mid).getString("username"));
            if (result == 0) {
                return mid;
            } else if (result > 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    @Override
    public User getUser(String username) {
        JSONArray users = this.getUsers();
        int index = this.search(users, username);
        if (index != -1) {
            JSONObject user = users.getJSONObject(index);
            return new User(user.getString("username"), user.getString("hash"), user.getString("salt"));
        }
        return null;
    }
}
