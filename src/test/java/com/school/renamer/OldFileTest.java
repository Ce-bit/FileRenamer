package com.school.renamer;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.jupiter.api.BeforeEach; //previously Before
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



public class OldFileTest {
    OldFile studentFile;
    File test_file;
    String filePath = "src/test/java/com/school/renamer/";
    //

    public OldFileTest(){

    }
    @BeforeEach
    public void InitFile() throws IOException{
        Files.createDirectories(Paths.get(filePath));
        test_file = new File("0123456789-111111_John_Doe_123456_testfile.pdf");
        studentFile = new OldFile(test_file);
    }
    @Test
    public void testGetID(){
        String expected_value = "123456";
        String actual_value = studentFile.getID(studentFile.oldName);
        assertEquals(expected_value, actual_value);
    }

    @Test
    public void testGetID_ID_NOT_FOUND(){
        CorrectName john = new CorrectName("John Doe", "456789");
        String expected_value = "ID NOT FOUND!";
        String actual_value = studentFile.getID(john.getCCC());
        assertEquals(expected_value, actual_value);
    }

    @Test
    public void testGetCSV(){
        CorrectName jardel = new CorrectName("Jardel Davis", "123456");
        CorrectName aleksi = new CorrectName("Aleksi Oliviere", "654321");
        CorrectName jamal = new CorrectName("Jamal Ali", "012345");
        CorrectName javan = new CorrectName("Javan Pierre", "543210");
        studentFile.correctNames.add(jardel);
        studentFile.correctNames.add(aleksi);
        studentFile.correctNames.add(jamal);
        studentFile.correctNames.add(javan);
        String expected_value = jardel.getFullName() + "_" + jardel.getCCC() + "_assignsubmission_file_";
        String actual_value = studentFile.getCSV(studentFile.oldName);
        assertEquals(expected_value, actual_value);
    }

    @Test
    public void testGetCSV_IDNotFOUND(){
        
        String expected_value = "ID NOT FOUND!";
        String actual_value = studentFile.getCSV(studentFile.oldName);
        assertEquals(expected_value, actual_value);
    }

    @Test
    public void testCorrectFile(){
        String expResult = studentFile.getCSV(studentFile.oldName) + studentFile.oldName;
        String result = studentFile.correctFile();
        assertEquals(expResult, result);
    }

   
}
