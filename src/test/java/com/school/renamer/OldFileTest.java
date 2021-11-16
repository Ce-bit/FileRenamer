package com.school.renamer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;


public class OldFileTest 
{
    private OldFile bobFile;
    ArrayList<CorrectName> names = new ArrayList<>();

    public OldFileTest(){
    }

    @BeforeEach
    public void initializeOldFileObjects() {
        File srcFile = new File("1234567890-123456_Bob_Flounder_999999_bla bla.pdf");
        bobFile = new OldFile(srcFile);
    }

    @Test
    public void testGetID() {
        //System.out.println("getFullName"); 
        String expResult = "999999";
        String result = bobFile.getID(bobFile.getOname());
        assertEquals(expResult, result);
    }

    @Test
    public void testGetCSV() {      
        CorrectName bob = new CorrectName("Bob Flounder", "999999");
        CorrectName frank = new CorrectName("Frank Jameson", "888888");
        bobFile.correctNames.add(bob);
        bobFile.correctNames.add(frank);
        System.out.println(bobFile.getID(bobFile.oldName));
        String expResult = bob.getFullName() + "_" + bob.getCCC() + "_" + "assignsubmission_file_";
        String result = bobFile.getCSV(bobFile.oldName);
        assertEquals(expResult, result);
    }

    @Test
    public void testCorrectFile() {
        //System.out.println("getCCC"); 
        String expResult = bobFile.getCSV(bobFile.oldName) + bobFile.oldName;
        String result = bobFile.correctFile();
        assertEquals(expResult, result);
    }
}
