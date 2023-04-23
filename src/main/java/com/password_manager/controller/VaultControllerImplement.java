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
    public Vault getVault(String name) {
        JSONServiceImplement jsonService = new JSONServiceImplement("./db/" + this.username + "/vault.json");
        JSONObject obj = jsonService.getObject().getJSONObject(name);
        return new Vault(obj.getString("name"), obj.getString("username"), obj.getString("password"),
                obj.getString("url"));
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
    public boolean updateVault(Vault vault, String name) {
        JSONServiceImplement jsonService = new JSONServiceImplement("./db/" + this.username + "/vault.json");
        JSONObject obj = jsonService.getObject().getJSONObject(name);
        obj.put("name", vault.getName());
        obj.put("username", vault.getUsername());
        obj.put("password", vault.getPassword());
        obj.put("url", vault.getUrl());
        return jsonService.saveObject(name, obj);
    }

}
