package com.password_manager.services.interfaces;

import org.json.JSONObject;
import org.json.JSONArray;

public interface JSONService {
    /**
     * Return the users on the file in a JSONObject.
     * @return 
     */
    JSONObject getObject();

    /**
     * Return the users on the file in a JSONArray.
     * @return
     */
    JSONArray getList();

    /**
     * Save Json object on the file.
     * @param key 
     * @param obj
     * @param list 
     * @return
     */
    boolean saveObject(String key, JSONObject obj);
    boolean saveObject(String key);
    boolean saveObject(JSONArray list);
}
