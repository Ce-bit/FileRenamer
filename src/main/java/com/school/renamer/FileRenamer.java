package com.school.renamer;
import java.io.File;
import java.util.List;

public class FileRenamer {
    FileOps command; 
    String separator = System.getProperty( "file.separator" );
    String originalFolderPath = "src/main/java/com/school/renamer/filesToRename";
    String originalOutputPath = "src/main/java/com/school/renamer/filesToRename/renamedFiles";

    public void renameFile(List<File> pdfList, List<CorrectName> csvList){
        if(!pdfList.isEmpty()){
            for(File file: pdfList){
                OldFile oFile = new OldFile(file);

                if (!oFile.getID(oFile.getOname()).equals("nomatch")) {
                    oFile.correctNames.addAll(csvList);
                    String newFile = oFile.correctFile();
                }
                File newName = new File(file.getParent() + separator + newFile);

        }
    }   
   }
}  
