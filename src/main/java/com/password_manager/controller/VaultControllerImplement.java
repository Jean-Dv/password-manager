package com.password_manager.controller;

import org.json.JSONObject;

import com.password_manager.controller.interfaces.VaultController;
import com.password_manager.models.Vault;
import com.password_manager.services.JSONServiceImplement;

public class VaultControllerImplement implements VaultController {

    String username;
    public VaultControllerImplement(String username) {
        this.username = username;
    }

    @Override
    public String getVault(String name) {
        JSONServiceImplement jsonService = new JSONServiceImplement("./db/" + this.username + "/vault.json");
        return jsonService.getObject().getJSONObject(name).toString();
    }

    @Override
    public boolean addVault(String name, String username, String password, String url) {
        JSONServiceImplement jsonService = new JSONServiceImplement("./db/" + this.username + "/vault.json");
        return jsonService.saveObject(name, new Vault(name, username, password, url));
    }

    @Override
    public boolean removeVault(String name) {
        JSONServiceImplement jsonService = new JSONServiceImplement("./db/" + this.username + "/vault.json");
        return jsonService.saveObject(name);
    }

    @Override
    public boolean updateVault(String name, String username, String password, String url) {
        JSONServiceImplement jsonService = new JSONServiceImplement("./db/" + this.username + "/vault.json");
        JSONObject obj = jsonService.getObject().getJSONObject(name);
        obj.put("username", username);
        obj.put("password", password);
        obj.put("url", url);
        obj.put("name", name);
        return jsonService.saveObject(name, obj);
    }
    
}
