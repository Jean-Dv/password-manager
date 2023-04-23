package com.password_manager.models;

import org.json.JSONObject;

public class Vault extends JSONObject {
    private String name;
    private String username;
    private String password;
    private String url;

    public Vault(String name, String username, String password, String url) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.url = url;
        this.put("name", this.name);
        this.put("username", this.username);
        this.put("password", this.password);
        this.put("url", this.url);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
        this.put("name", name);
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
        this.put("username", this.username);
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
        this.put("password", this.password);
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
        this.put("url", this.url);
    }

    @Override
    public String toString() {
        return "[*] Name: " + this.name + "\n[*] Username: " + this.username + "\n[*] Password: " + this.password
                + "\n[*] URL: " + this.url + "\n";
    }
}
