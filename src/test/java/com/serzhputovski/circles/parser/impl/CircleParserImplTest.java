package com.serzhputovski.circles.parser.impl;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Optional;

import static org.testng.Assert.*;

public class CircleParserImplTest {
    private CircleParserImpl parser;

    @BeforeClass
    public void setup() {
        parser = new CircleParserImpl();
    }

    @Test
    public void testParseValidInput() {
        Optional<double[]> result = parser.parse("1.0 2.0 3.0");
        assertTrue(result.isPresent());
        assertEquals(result.get()[0], 1.0);
        assertEquals(result.get()[1], 2.0);
        assertEquals(result.get()[2], 3.0);
    }

    @Test
    public void testParseInputWithExtraSpaces() {
        Optional<double[]> result = parser.parse("   4.0   5.0   6.0   ");
        assertTrue(result.isPresent());
        assertEquals(result.get()[0], 4.0);
        assertEquals(result.get()[1], 5.0);
        assertEquals(result.get()[2], 6.0);
    }

    @Test
    public void testParseInvalidInputFormat() {
        Optional<double[]> result = parser.parse("1.0 2.0");
        assertFalse(result.isPresent());
    }

    @Test
    public void testParseEmptyInput() {
        Optional<double[]> result = parser.parse("");
        assertFalse(result.isPresent());
    }

    @Test
    public void testParseInputWithNegativeRadius() {
        Optional<double[]> result = parser.parse("1.0 2.0 -3.0");
        assertTrue(result.isPresent());
        assertEquals(result.get()[0], 1.0);
        assertEquals(result.get()[1], 2.0);
        assertEquals(result.get()[2], -3.0);
    }
}