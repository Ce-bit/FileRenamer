package com.school.renamer;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Scanner;  
import java.io.IOException;
import java.io.FileWriter;


public class FileFixer {
    public static void main(String[] argv) throws IOException {
        int flags = 0;
        int renamed = 0;
        int missing = 0;
        String separator = System.getProperty( "file.separator" );
        String extension = "";
        String identifier = "";
        String wholeName = "";

        String originalFolderPath = "src/main/java/com/school/renamer/filesToRename";
        String originalOutputPath = "src/main/java/com/school/renamer/filesToRename/renamedFiles";
        
        //replaced "/" with system separator property to remove OS-dependancy
        String adjFolderPath = originalFolderPath.replace("/",separator);
        String adjOutputPath = originalOutputPath.replace("/",separator);

        //FileWriter for storing missing submissions in a txt file located at project root
        FileWriter myWriter = new FileWriter("src/main/java/com/school/renamer/MissingList.txt");
        ArrayList<CorrectName> csvEntries = new ArrayList<>();
        ArrayList<File> oldFiles = new ArrayList<>();
        
        File myFolder = new File(adjFolderPath);
        File[] arrayFile = myFolder.listFiles();
        for (File file : arrayFile) {
            if (file.isFile()) {
                //get full filename
                String longFileName = file.getName();

                //check if file is pdf or csv and process accordingly
                int i = longFileName.lastIndexOf('.');
                if (i >= 0) { 
                    extension = longFileName.substring(i+1);
                }

                //if file is a CSV, extract CorrectName objects
                if (extension.equals("csv")) {
                    Scanner sc = new Scanner(new File(file.getPath()));
                    sc.useDelimiter(",");
                    String skipHeader = sc.nextLine();
                    while (sc.hasNext()) {  
                        identifier = sc.next();
                        identifier = identifier.replaceAll("\\D+","");
                        wholeName = sc.next();
                        CorrectName csvEntry = new CorrectName(wholeName, identifier);
                        csvEntries.add(csvEntry);                       
                        String skipTheRest = sc.nextLine();
                    }    
                }
                //if file is PDF, copy all file names to oldFiles
                else if (extension.equals("pdf")){
                    oldFiles.add(file);
                }
                //any other file type...
                else {
                    System.out.println("Flagged invalid submission:" + file.getName());
                    flags++;
                }
            }
        }
        
        Files.createDirectories(Paths.get(adjOutputPath));

        //Process pdf files
        if (!oldFiles.isEmpty()) {
            for (File ofiles : oldFiles) {
                OldFile oFile = new OldFile(ofiles);

                //Process valid submissions
                if (!oFile.getID(oFile.getOname()).equals("nomatch")) {
                    oFile.correctNames.addAll(csvEntries);
                    String newFile = oFile.correctFile();

                    //Check for missing submissions and flag their names
                    for (CorrectName nameChecks : csvEntries) {
                        if (newFile.contains(nameChecks.getFullName())) {
                            nameChecks.found = true;
                        }
                    }

                    //rename valid submissions
                    File newName = new File(ofiles.getParent() + separator + newFile);
                    renamed++;

                    //copy renamed file to /renamedFiles
                    oFile.copyFile(Paths.get(ofiles.getPath()), Paths.get(adjOutputPath + separator + newName.getName()), StandardCopyOption.REPLACE_EXISTING);
                }

                //Print invalid submission flags
                else {
                    System.out.println("Flagged invalid sumbission: " + ofiles.getName());
                    flags++;
                }   
            }
            //If some pdfs are missing, store all the missing names in MissingList.txt
            try {  
                for (CorrectName names : csvEntries) {
                    if (!names.found){
                        missing++;
                        myWriter.write("Missing submission file for " + names.getFullName() + "\n");
                        System.out.println("Successfully wrote to the Missing list.");   
                    } 
                }
                myWriter.close();
              } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
              }

        }
        //If all submissions are missing, store all the missing names in MissingList.txt
        else {
            for (CorrectName names : csvEntries) {
                missing++;
                myWriter.write("Missing submission file for " + names.getFullName() + "\n");
                System.out.println("Successfully wrote to the Missing list.");  
            }
             
        }

        //Report the issues that FileFixer fixed
        if (flags > 0) {
            System.out.println("Invalid submissions: " + flags);
        }
        if (renamed > 0) {
            System.out.println("Renamed submissions: " + renamed);
        }
        if (missing > 0) {
            System.out.println("Missing submissions: " + missing);
        }      
    }
}