import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
    public static void main(String[] args) {
        String path = "filesToRename/Sample 3 CSV.csv";
        String line = "";
        List<CorrectName> nameList = new ArrayList<>();
        OldFile oldFile = new OldFile(new File("filesToRename/1409121490-602637_Beth_Morales-Horton_601683_Assignment1_81305512.pdf"));
        File my_folder = new File("\\filesToRename");
        File[] array_file = my_folder.listFiles();
        try{
            BufferedReader br = new BufferedReader(new FileReader(path));
            while((line = br.readLine()) != null){
                String[] values = line.split(",");
                //NNN = new File("filesToRename/1409121490-602637_Beth_Morales-Horton_601683_Assignment1_81305512.pdf").getName();
                //System.out.println(values[1] + "_" + values[0].replace("Participant ", "")+ "_assignmentsubmission_file_"+ NNN);
                CorrectName name = new CorrectName(values[1], values[0].replace("Participant ", ""));
                //System.out.println(name.correctedFileName()); 
                oldFile.addName(name);
                
            }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
        for(int i=0; i<array_file.length;i++){
            if(array_file[i].isFile()){
                File myFile = new File("")
            }
        }

        System.out.println(oldFile.correctFile());
        myFile.renameTo(new File("filesToRename/"+oldFile.correctFile()));


    }
}
