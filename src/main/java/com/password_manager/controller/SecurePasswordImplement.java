package com.password_manager.controller;

import com.password_manager.controller.interfaces.SecurePasswordController;
import com.password_manager.models.SecurePassword;

public class SecurePasswordImplement implements SecurePasswordController {
    @Override
    public String generatePassword(SecurePassword securePassword) {
        String password = "";
        String numbers = "0123456789";
        String symbols = "!@#$%^&*_=+-/.?<>)";
        String uppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowercase = "abcdefghijklmnopqrstuvwxyz";
        String characters = "";

        if (securePassword.getHasNumbers()) {
            characters += numbers;
        }

        if (securePassword.getHasSymbols()) {
            characters += symbols;
        }

        if (securePassword.getHasUppercase()) {
            characters += uppercase;
        }

        if (securePassword.getHasLowercase()) {
            characters += lowercase;
        }

        for (int i = 0; i < securePassword.getLength(); i++) {
            int index = (int) (Math.random() * characters.length());
            password += characters.charAt(index);
        }

        return password;
    }
}
