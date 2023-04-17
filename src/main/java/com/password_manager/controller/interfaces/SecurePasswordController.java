package com.password_manager.controller.interfaces;

import com.password_manager.models.SecurePassword;

public interface SecurePasswordController {
    /**
     * Generate a secure password.
     * @param length
     * @param hasNumbers
     * @param hasSymbols
     * @param hasUppercase
     * @param hasLowercase
     * @return
     */
    String generatePassword(SecurePassword securePassword);
}
