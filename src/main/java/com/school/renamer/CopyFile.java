package com.school.renamer;

import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;

public class CopyFile{
    
    public void copyFile(Path src, Path dest, CopyOption option){
        try {
            Files.copy(src, dest, option);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
