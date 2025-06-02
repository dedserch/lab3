package com.serzhputovski.circles.validator.impl;

import com.serzhputovski.circles.validator.CircleValidator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class CircleValidatorImplTest {
    private CircleValidator validator;

    @BeforeClass
    public void setup() {
        validator = new CircleValidatorImpl();
    }

    @Test
    public void testValidRadius() {
        assertTrue(validator.validateRadius(1.0));
    }

    @Test
    public void testInvalidRadius() {
        assertFalse(validator.validateRadius(-1.0));
    }

    @Test
    public void testValidCoordinateX() {
        assertTrue(validator.validateCoordinateX(0.0));
    }

    @Test
    public void testInvalidCoordinateX() {
        assertFalse(validator.validateCoordinateX(5001.0));
    }

    @Test
    public void testValidCoordinateY() {
        assertTrue(validator.validateCoordinateY(0.0));
    }

    @Test
    public void testInvalidCoordinateY() {
        assertFalse(validator.validateCoordinateY(-5001.0));
    }
}