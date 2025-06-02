package com.serzhputovski.circles.service.impl;

import com.serzhputovski.circles.entity.Circle;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.testng.Assert.*;


public class CircleServiceImplTest {
    private CircleServiceImpl service;
    private List<Circle> circles;

    @BeforeClass
    public void setup() {
        service = new CircleServiceImpl();
        circles = Arrays.asList(
                new Circle(0, 0, 1),
                new Circle(0, 0, 2),
                new Circle(0, 0, 3)
        );
    }

    @Test
    public void testFindCircleWithMaxArea() {
        Optional<Circle> result = service.findCircleWithMaxArea(circles);
        assertEquals(result.get().getRadius(), 3.0, 0.001);
    }

    @Test
    public void testFindCircleWithMinArea() {
        Optional<Circle> result = service.findCircleWithMinArea(circles);
        assertEquals(result.get().getRadius(), 1.0, 0.001);
    }

    @Test
    public void testFindCircleWithMaxPerimeter() {
        Optional<Circle> result = service.findCircleWithMaxPerimeter(circles);
        assertEquals(result.get().getRadius(), 3.0, 0.001);
    }

    @Test
    public void testFindCirclesOnSameLine() {
        List<Circle> lineCircles = Arrays.asList(
                new Circle(0, 0, 1),
                new Circle(0, 2, 1),
                new Circle(0, 4, 1)
        );
        ArrayList<ArrayList<Circle>> result = service.findCirclesOnSameLine(lineCircles);
        assertEquals(result.size(), 1);
    }

    @Test
    public void testFindCircleWithMaxAreaEmptyList() {
        Optional<Circle> result = service.findCircleWithMaxArea(new ArrayList<>());
        assertFalse(result.isPresent());
    }

    @Test
    public void testFindCircleWithMinAreaEmptyList() {
        Optional<Circle> result = service.findCircleWithMinArea(new ArrayList<>());
        assertFalse(result.isPresent());
    }

    @Test
    public void testFindCircleWithIdenticalCircles() {
        List<Circle> identicalCircles = Arrays.asList(
                new Circle(1, 1, 2),
                new Circle(1, 1, 2),
                new Circle(1, 1, 2)
        );
        Optional<Circle> result = service.findCircleWithMaxArea(identicalCircles);
        assertEquals(result.get().getRadius(), 2.0, 0.001);
    }

    @Test
    public void testFindCirclesOnSameLineNoCircles() {
        List<Circle> emptyCircles = new ArrayList<>();
        ArrayList<ArrayList<Circle>> result = service.findCirclesOnSameLine(emptyCircles);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testFindCirclesOnSameLineDifferentLines() {
        List<Circle> differentLineCircles = Arrays.asList(
                new Circle(0, 0, 1),
                new Circle(2, 2, 1),
                new Circle(4, 4, 1)
        );
        ArrayList<ArrayList<Circle>> result = service.findCirclesOnSameLine(differentLineCircles);
        assertTrue(result.get(0).size() ==3);
    }

    @Test
    public void testFindCirclesOnSameLineMultipleLines() {
        List<Circle> mixedLineCircles = Arrays.asList(
                new Circle(0, 0, 1),
                new Circle(0, 2, 1),
                new Circle(3, 3, 1),
                new Circle(0, 4, 1)
        );
        ArrayList<ArrayList<Circle>> result = service.findCirclesOnSameLine(mixedLineCircles);
        assertEquals(result.size(), 4);
    }
}