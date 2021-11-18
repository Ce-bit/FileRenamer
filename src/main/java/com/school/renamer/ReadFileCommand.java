package com.school.renamer;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public class ReadFileCommand implements FileOps{

    FileReader fileReader = new FileReader();
    private List<CorrectName> csvList;
    private File csvFile;

    public ReadFileCommand(File csvFile, List<CorrectName> csvList){
        this.csvFile = csvFile;
        this.csvList = csvList;
    }
    

	public void executeRead(File csvFile, List<CorrectName> csvList){
        try {
            fileReader.readCSV(csvFile, csvList);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }	
	}

    @Override
    public void execute(){
        executeRead(csvFile,csvList);
    }

}
