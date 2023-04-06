package com.password_manager.services;

import com.password4j.Hash;
import com.password4j.Password;
import com.password_manager.services.interfaces.EncryptService;

public class EncryptServiceImplement implements EncryptService {
    @Override
    public Hash encryptPassword(String password) {
        Hash hash = Password.hash(password).addRandomSalt().withPBKDF2();
        return hash;
    }

    @Override
    public boolean verifyPassword(String originalPassword, String hashPassword) {
        return true;
    }
}
