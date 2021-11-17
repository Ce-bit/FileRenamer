package com.school.renamer;
public class CorrectName {
    String fullName, CCC;
    boolean found = false;

    public CorrectName(String fullName, String CCC) {
        this.fullName = fullName;
        this.CCC = CCC;
    }

    public String getFullName() {
        return fullName;
    }

    public String getCCC() {
        return CCC;
    }

    public boolean isFound(){
        return found;
    }

    public String correctedFileName() {
        String fileName = "";
        fileName = fileName + fullName + "_" + CCC + "_" + "assignsubmission_file_";
        return fileName;
    }
}
