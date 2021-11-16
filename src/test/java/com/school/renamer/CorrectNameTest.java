package com.school.renamer;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;


public class CorrectNameTest 
{
    private CorrectName bob;

    public CorrectNameTest(){
    }

    @BeforeEach
    public void initializeCorrectNameObjects() {
        bob = new CorrectName("Bob Flounder", "999999");
    }

    @Test
    public void testGetFullName() {
        //System.out.println("getFullName"); 
        String expResult = "Bob Flounder";
        String result = bob.getFullName();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetCCC() {
        //System.out.println("getCCC"); 
        String expResult = "999999";
        String result = bob.getCCC();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetCorrectedFileName() {
        //System.out.println
        String expResult = bob.getFullName() + "_" + bob.getCCC() + "_" + "assignsubmission_file_";
        String result = bob.correctedFileName();
        assertEquals(expResult, result);
    }
}
