package com.password_manager.models;

import org.json.JSONObject;

public class User extends JSONObject {

    private String username;
    private String hash;
    private String salt;

    public User(String username, String hash, String salt) {
        this.username = username;
        this.hash = hash;
        this.salt = salt;
        this.put("username", username);
        this.put("hash", hash);
        this.put("salt", salt);
    }


    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
        this.put("username", username);
    }

    public String getHash() {
        return this.hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
        this.put("hash", hash);
    }

    public String getSalt() {
        return this.salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
        this.put("salt", salt);
    }
}
