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

    /**
     * Get hash password from the file.
     * @param username
     * @return
     */
    String getHashPassword(String username);

    /**
     * Get salt from the file.
     * @param username
     * @return
     */
    String getSalt(String username);

    /**
     * Sign in the user.
     * @param username
     * @param password
     * @return
     */
    boolean signIn(String username, String password);
}
