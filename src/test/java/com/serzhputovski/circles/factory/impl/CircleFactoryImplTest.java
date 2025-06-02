package com.serzhputovski.circles.factory.impl;

import com.serzhputovski.circles.entity.Circle;
import com.serzhputovski.circles.factory.CircleFactory;
import com.serzhputovski.circles.validator.CircleValidator;
import com.serzhputovski.circles.validator.impl.CircleValidatorImpl;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Optional;

import static org.testng.Assert.*;

public class CircleFactoryImplTest {
    private CircleValidator validator;
    private CircleFactory factory;

    @BeforeClass
    public void setup() {
        validator = new CircleValidatorImpl();
        factory = new CircleFactoryImpl(validator);
    }

    @Test
    public void testCreateValidCircle() {
        Optional<Circle> result = factory.create(0, 0, 1);
        assertTrue(result.isPresent());
    }

    @Test
    public void testCreateInvalidRadius() {
        Optional<Circle> result = factory.create(0, 0, -1);
        assertFalse(result.isPresent());
    }

    @Test
    public void testCreateInvalidCoordinates() {
        Optional<Circle> result = factory.create(6000, 0, 1);
        assertFalse(result.isPresent());
    }

    @Test
    public void testCreateCircleWithZeroRadius() {
        Optional<Circle> result = factory.create(0, 0, 0);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testCreateCircleWithLargeValidCoordinates() {
        Optional<Circle> result = factory.create(3000, 3000, 1);
        assertTrue(result.isPresent());
        assertEquals(result.get().getX(), 3000.0);
        assertEquals(result.get().getY(), 3000.0);
    }

    @Test
    public void testCreateCircleWithEdgeCoordinates() {
        Optional<Circle> result = factory.create(-6000, 0, 1);
        assertFalse(result.isPresent());
    }

    @Test
    public void testCreateMultipleCircles() {
        Optional<Circle> circle1 = factory.create(1, 1, 1);
        Optional<Circle> circle2 = factory.create(2, 2, 2);
        Optional<Circle> circle3 = factory.create(3, 3, 3);

        assertTrue(circle1.isPresent());
        assertTrue(circle2.isPresent());
        assertTrue(circle3.isPresent());

        assertEquals(circle1.get().getRadius(), 1.0);
        assertEquals(circle2.get().getRadius(), 2.0);
        assertEquals(circle3.get().getRadius(), 3.0);
    }

    @Test
    public void testCreateCircleWithValidFractionalRadius() {
        Optional<Circle> result = factory.create(0, 0, 2.5);
        assertTrue(result.isPresent());
        assertEquals(result.get().getRadius(), 2.5);
    }
}