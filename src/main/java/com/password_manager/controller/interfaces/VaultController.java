package com.password_manager.controller.interfaces;

public interface VaultController {
    /**
     * Get a vault from the file.
     * @param name
     * @return
     */
    String getVault(String name);

    /**
     * Add a new vault to the file.
     * @param name
     * @param username
     * @param password
     * @param url
     * @return
     */
    boolean addVault(String name, String username, String password, String url);

    /**
     * Remove a vault from the file.
     * @param name
     * @return
     */
    boolean removeVault(String name);

    /**
     * Update a vault on the file.
     * @param name
     * @param username
     * @param password
     * @param url
     * @return
     */
    boolean updateVault(String name, String username, String password, String url);

}
