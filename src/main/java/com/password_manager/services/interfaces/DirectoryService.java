package com.password_manager.services.interfaces;

public interface DirectoryService {
    /**
     * Get the current directory
     * @return
     */
    String getCurrentDirectory();

    /**
     * Write a file to the current directory
     * @param directoryName
     */
    void writeFile(String directoryName);

}
