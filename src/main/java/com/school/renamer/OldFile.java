package com.school.renamer;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.CopyOption;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OldFile implements FileOps{
    protected String oldName;
    protected ArrayList<CorrectName> correctNames;

    public OldFile() {
    }

    

    public OldFile(File srcFile) {
        this.oldName = srcFile.getName();
        this.correctNames = new ArrayList<>();
    }

    public void copyFile(Path src, Path dest, CopyOption option) {
        try {
            Files.copy(src, dest, option);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getOname(){
        return this.oldName;
    }

    public String getID(final String oldName) {
        String[] my_token = oldName.split("_", 2);
        final Pattern p = Pattern.compile("(\\d{6})");
        final Matcher m = p.matcher(my_token[1]);
        if (m.find()) {
            return m.group(0);
        }
        return "";
    }

    public String getCSV(String oldName) {
        String id = getID(oldName);
        for (CorrectName n : correctNames) {
            if (n.getCCC().equals(id)) {
                return n.correctedFileName();
            }
        }
        return "ID NOT FOUND!";
    }

    public String correctFile() {
        String fileName = getCSV(oldName);
        fileName = fileName + oldName;
        return fileName;
    }

}
