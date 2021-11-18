package com.school.renamer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class FileReader {

    public void readCSV(File csvFile, List<CorrectName> csvList) throws FileNotFoundException {
        String fullName = "";
        String identifier = "";

        Scanner sc = new Scanner(new File(csvFile.getPath()));
        sc.useDelimiter(",");

        while (sc.hasNext()) {
            identifier = sc.next();
            identifier = identifier.replaceAll("\\D+", "");
            fullName = sc.next();
            CorrectName csvEntry = new CorrectName(fullName, identifier);
            csvList.add(csvEntry);

        }
    }

    public void readPDF(File pdfFile, List<File> pdfList) {
        pdfList.add(pdfFile);
    }

}
