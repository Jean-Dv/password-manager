package com.password_manager.models;

public class SecurePassword {
    private int length;
    private boolean hasNumbers;
    private boolean hasSymbols;
    private boolean hasUppercase;
    private boolean hasLowercase;

    public SecurePassword(int length, boolean hasNumbers, boolean hasSymbols, boolean hasUppercase, boolean hasLowercase) {
        this.length = length;
        this.hasNumbers = hasNumbers;
        this.hasSymbols = hasSymbols;
        this.hasUppercase = hasUppercase;
        this.hasLowercase = hasLowercase;
    }

    public int getLength() {
        return this.length;
    }

    public void setLength(int length) {
        this.length = length;
    }
    
    public boolean getHasNumbers() {
        return this.hasNumbers;
    }

    public void setHasNumbers(boolean hasNumbers) {
        this.hasNumbers = hasNumbers;
    }

    public boolean getHasSymbols() {
        return this.hasSymbols;
    }

    public void setHasSymbols(boolean hasSymbols) {
        this.hasSymbols = hasSymbols;
    }

    public boolean getHasUppercase() {
        return this.hasUppercase;
    }

    public void setHasUppercase(boolean hasUppercase) {
        this.hasUppercase = hasUppercase;
    }

    public boolean getHasLowercase() {
        return this.hasLowercase;
    }

    public void setHasLowercase(boolean hasLowercase) {
        this.hasLowercase = hasLowercase;
    }
}
