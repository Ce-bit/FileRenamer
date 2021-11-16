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
        int flags = 0, renamed = 0, missing = 0;
        String separator = System.getProperty( "file.separator" );
        String extension = "";
        String identifier = "";
        String whole_name = "";

        //FileWriter for storing missing submissions in a txt file located at project root
        FileWriter myWriter = new FileWriter("MissingList.txt");
        ArrayList<CorrectName> csvEntries = new ArrayList<>();
        ArrayList<File> old_files = new ArrayList<>();
        
        String path_to_folder0 = "src/main/java/com/school/renamer/filesToRename";
        String path_to_output0 = "src/main/java/com/school/renamer/filesToRename/renamedFiles";
        
        //replaced "/" with system separator property to remove OS-dependancy
        String path_to_folder = path_to_folder0.replace("/",separator);
        String path_to_output = path_to_output0.replace("/",separator);
        File my_folder = new File(path_to_folder);
        File[] array_file = my_folder.listFiles();
        for (File file : array_file) {
            if (file.isFile()) {
                //get full filename
                String long_file_name = file.getName();

                //check if file is pdf or csv and process accordingly
                int i = long_file_name.lastIndexOf('.');
                if (i >= 0) { 
                    extension = long_file_name.substring(i+1);
                }

                //if file is a CSV, extract CorrectName objects
                if (extension.equals("csv")) {
                    Scanner sc = new Scanner(new File(file.getPath()));
                    sc.useDelimiter(",");
                    String skipHeader = sc.nextLine();
                    while (sc.hasNext()) {  
                        identifier = sc.next();
                        identifier = identifier.replaceAll("\\D+","");
                        whole_name = sc.next();
                        CorrectName csvEntry = new CorrectName(whole_name, identifier);
                        csvEntries.add(csvEntry);                       
                        String skipTheRest = sc.nextLine();
                    }    
                }
                //if file is PDF, copy all file names to old_files
                else if (extension.equals("pdf")){
                    old_files.add(file);
                }
                //any other file type...
                else {
                    System.out.println("Flagged invalid submission:" + file.getName());
                    flags++;
                }
            }
        }
        
        //traverse copied old_files ArrayList, 
        //create OldFile objects for each file in old_files, 
        //create new names for files using methods from OldFile, 
        //rename files using the created names and renamteTo(),
        //move renamed files into newlty created renamedFiles folder
        Files.createDirectories(Paths.get(path_to_output));

        //Process pdf files
        if (!old_files.isEmpty()) {
            for (File ofiles : old_files) {
                OldFile oFile = new OldFile(ofiles);

                //Process valid submissions
                if (!oFile.getID(oFile.getOname()).equals("nomatch")) {
                    oFile.correctNames.addAll(csvEntries);
                    String new_file = oFile.correctFile();

                    //Check for missing submissions and flag their names
                    for (CorrectName nameChecks : csvEntries) {
                        if (new_file.contains(nameChecks.getFullName())) {
                            nameChecks.found = true;
                        }
                    }

                    //rename valid submissions
                    File oldName = new File(ofiles.getPath());
                    File newName = new File(ofiles.getParent() + separator + new_file);
                    oldName.renameTo(newName);
                    renamed++;

                    //copy renamed file to /renamedFiles
                    oFile.copyFile(Paths.get(newName.getPath()), Paths.get(path_to_output + separator + newName.getName()), StandardCopyOption.REPLACE_EXISTING);
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
                    if (names.found == false) {
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

