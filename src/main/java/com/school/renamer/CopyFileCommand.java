package com.school.renamer;

import java.nio.file.CopyOption;
import java.nio.file.Path;

public class CopyFileCommand implements FileOps {
    
    CopyFile copyFile = new CopyFile();
    Path src;
    Path dest;
    CopyOption option;

    public CopyFileCommand(CopyFile copyFile, Path src, Path dest, CopyOption option){
        this.copyFile=copyFile;
        this.src = src;
        this.dest = dest;
        this.option = option;
    }
    
    @Override
    public void execute(){
        copyFile.copyFile(src, dest, option);
    }
}
