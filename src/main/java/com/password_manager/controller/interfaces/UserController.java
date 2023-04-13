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
    void createUser(String username, String password);

    /**
     * Sign in the user.
     * @param username
     * @param password
     * @return
     */
    boolean signIn(String username, String password);
}
