package com.password_manager.services;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.password_manager.services.interfaces.DirectoryService;

public class DirectoryServiceImplement implements DirectoryService {
    @Override
    public String getCurrentDirectory() {
        return System.getProperty("user.dir");
   }

    @Override
    public void writeFile(String directoryName) {
        String path = this.getCurrentDirectory().concat("/db/").concat(directoryName);
        String fileName = "vault.json";

        File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        File file = new File(path.concat("/").concat(fileName));
        try {
            FileWriter writer = new FileWriter(file.getAbsolutePath());
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write("{}");
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
