import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OldFile {
    private String oldName;
    private List<CorrectName> correctNames = new ArrayList<>();

    public OldFile(File srcFile) {
         this.oldName = srcFile.getName();
    }

    public void addName(CorrectName name){
        correctNames.add(name);
    }

    public String getID(final String oldName) {
        String[] values = oldName.split("_");
        for(CorrectName n: correctNames){
            for(int i=0;i<values.length;i++){
                if(n.getCCC().equals(values[i])){
                    return values[i];
                }
            }
        }
        // final Pattern p = Pattern.compile("(\\d{6})");
        // final Matcher m = p.matcher(oldName);
        // if (m.find()) {
        //     return m.group(0);
        // }
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
