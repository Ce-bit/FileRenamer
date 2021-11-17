package com.school.renamer;
import org.junit.jupiter.api.BeforeEach; //previously Before
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class CorrectNameTest {
    private CorrectName student;

    public CorrectNameTest(){

    }
    @BeforeEach
    public void setup(){
        student = new CorrectName("John Doe", "111111");
    }

    @Test
    public void testGetFullName(){
        String expectedName = "John Doe";
        String result = student.getFullName();
        assertEquals(expectedName, result);
    }

    @Test
    public void testGetCCC(){
        String expected_value = "111111";
        String result = student.getCCC();
        assertEquals(expected_value, result);
    }

    @Test
    public void testCorrectedFileName(){
        String expected_value = student.getFullName() + "_" + student.getCCC() + "_assignsubmission_file_";
        String result = student.correctedFileName();
        assertEquals(expected_value, result); 
    }


    
}
