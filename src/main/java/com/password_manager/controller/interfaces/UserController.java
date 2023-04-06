package com.password_manager.controller.interfaces;

import org.json.JSONArray;

public interface UserController {
    /**
     * Return the users on the file in a JSONArray.
     * @return
     */
    JSONArray getUsers();

    /**
     * Save the user on the file.
     * @param user
     */
    void saveUser(String username, String password);
}
