package com.serzhputovski.circles.reader.impl;

import com.serzhputovski.circles.exception.CircleFileException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class CircleFileReaderImplTest {
    private CircleFileReaderImpl reader;

    @BeforeClass
    public void setup() {
        reader = new CircleFileReaderImpl();
    }

    @Test
    public void testValidFile() throws CircleFileException {
        List<String> lines = reader.readFile("src/test/resources/valid.txt");
        assertFalse(lines.isEmpty());
    }

    @Test(expectedExceptions = CircleFileException.class)
    public void testNonexistentFile() throws CircleFileException {
        reader.readFile("nonexistent.txt");
    }

    @Test
    public void testEmptyFile() throws CircleFileException {
        List<String> lines = reader.readFile("src/test/resources/empty.txt");
        assertTrue(lines.isEmpty());
    }
}