package com.password_manager.services.interfaces;

import com.password4j.Hash;

public interface EncryptService {
    /**
     * Let us to encrypt the password
     * @param password
     * @return
     */
    Hash encryptPassword(String password);

    /**
     * Verify if the originar password and hash password are the same.
     * @param originalPassword
     * @param hashPassword
     * @param username
     * @return
     */
    boolean verifyPassword(String originalPassword, String hashPassword);
}