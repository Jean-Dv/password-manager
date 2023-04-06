package com.password_manager.services.interfaces;

import java.io.IOException;

import org.json.JSONObject;
import org.json.JSONArray;

public interface JsonToObjectService {
    /**
     * Return the users on the file in a JSONArray.
     * @return 
     * @throws IOException
     */
    JSONObject getObject();

    /**
     * Save Json object on the file.
     * @param key 
     * @param obj
     * @param list 
     * @return
     */
    boolean saveObject(String key, JSONObject obj);
    boolean saveObject(String key, JSONArray list);
}
