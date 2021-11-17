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

        //Check that valid submissions contain "_6-digitID_" string
        final Pattern p = Pattern.compile("(_\\d{6}_)");
        final Matcher m = p.matcher(oldName);
        if (m.find()) {
            return m.group(0).replace("_", "");
        }
        else {
            return "nomatch";
        }
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
