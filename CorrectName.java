
public class CorrectName {
    String fullName, CCC;

    public CorrectName(String Name, String CCC) {
        this.fullName = Name;
        this.CCC = CCC;
    }

    public String getFullName() {
        return fullName;
    }

    public String getCCC() {
        return CCC;
    }

    public String correctedFileName() {
        String fileName = "";
        fileName = fileName + fullName + "_" + CCC + "_assignsubmission_file_";
        return fileName;
    }
}
