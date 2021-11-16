package com.school.renamer;
import java.nio.file.CopyOption;
import java.nio.file.Path;

public interface FileOps {
    public void copyFile(Path srcFile, Path destFile, CopyOption option);
}
