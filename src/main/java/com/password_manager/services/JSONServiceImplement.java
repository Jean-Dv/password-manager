package com.password_manager.services;

import com.password_manager.services.interfaces.JSONService;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class JSONServiceImplement implements JSONService {

    private String content;
    private String path;

    public JSONServiceImplement(String path) {
        File file = new File(path);
        try {
            this.path = path;
            this.content = FileUtils.readFileToString(file, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public JSONObject getObject() {
        JSONObject obj = new JSONObject(this.content);
        return obj;
    }

    @Override
    public JSONArray getList() {
        JSONArray list = new JSONArray(this.content);
        return list;
    }

    @Override
    public boolean saveObject(String key, JSONObject obj) {
        JSONObject json = this.getObject();

        json.put(key, obj);
        try (PrintWriter out = new PrintWriter(new FileWriter(this.path))) {
            out.println(json.toString());
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    @Override
    public boolean saveObject(JSONArray list) {
        try (PrintWriter out = new PrintWriter(new FileWriter(this.path))) {
            out.println(list.toString());
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    }
}
