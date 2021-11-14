package com.school.renamer;
import java.io.File;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OldFile {
    protected String oldName;
    protected ArrayList<CorrectName> correctNames;

    public OldFile(File srcFile) {
        this.oldName = srcFile.getName();
        this.correctNames = new ArrayList<>();
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
