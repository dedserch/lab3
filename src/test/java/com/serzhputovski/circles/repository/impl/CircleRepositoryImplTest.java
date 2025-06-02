package com.serzhputovski.circles.repository.impl;

import com.serzhputovski.circles.entity.Circle;
import com.serzhputovski.circles.service.CircleService;
import com.serzhputovski.circles.service.impl.CircleServiceImpl;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class CircleRepositoryImplTest {
    private CircleRepositoryImpl repository;
    private CircleService circleService;
    private Circle circle1;
    private Circle circle2;
    private Circle circle3;

    @BeforeMethod
    public void setUp() {
        repository = new CircleRepositoryImpl();
        circleService = new CircleServiceImpl();

        // Create test circles with different properties
        circle1 = new Circle(1.0, 2.0, 3.0);
        circle2 = new Circle(4.0, 5.0, 1.0);
        circle3 = new Circle(7.0, 2.0, 2.0);
    }

    @Test
    public void testAdd_AddsSingleCircle() {
        repository.add(circle1);
        List<Circle> circles = repository.all();
        assertEquals(circles.size(), 1, "Should contain exactly one circle");
        assertTrue(circles.contains(circle1), "Should contain the added circle");
    }

    @Test
    public void testAdd_AddsMultipleCircles() {
        repository.add(circle1);
        repository.add(circle2);
        repository.add(circle3);
        List<Circle> circles = repository.all();
        assertEquals(circles.size(), 3, "Should contain all added circles");
    }

    @Test
    public void testAdd_DoesNotAddDuplicateCircles() {
        Circle duplicate = new Circle(1.0, 2.0, 3.0);
        repository.add(circle1);
        repository.add(duplicate);
        List<Circle> circles = repository.all();
        assertEquals(circles.size(), 2, "Should allow duplicate circle objects");
    }

    @Test
    public void testAll_ReturnsEmptyListInitially() {
        List<Circle> circles = repository.all();
        assertTrue(circles.isEmpty(), "Should return empty list for new repository");
    }

    @Test
    public void testAll_ReturnsDefensiveCopy() {
        repository.add(circle1);
        List<Circle> circles = repository.all();
        circles.clear();
        assertFalse(repository.all().isEmpty(), "Modifying returned list shouldn't affect repository");
    }

    @Test
    public void testAll_ReturnsAllAddedCircles() {
        repository.add(circle1);
        repository.add(circle2);
        repository.add(circle3);
        List<Circle> circles = repository.all();
        assertEquals(circles.size(), 3, "Should return all added circles");
    }

    @Test
    public void testSortByRadius_EmptyRepository() {
        List<Circle> sorted = repository.sortByRadius();
        assertTrue(sorted.isEmpty(), "Should return empty list for empty repository");
    }

    @Test
    public void testSortByRadius_SortsCorrectly() {
        repository.add(circle1); // radius=3
        repository.add(circle2); // radius=1
        repository.add(circle3); // radius=2
        List<Circle> sorted = repository.sortByRadius();
        assertEquals(sorted.get(0), circle2, "Smallest radius should be first");
        assertEquals(sorted.get(1), circle3, "Middle radius should be second");
        assertEquals(sorted.get(2), circle1, "Largest radius should be last");
    }

    @Test
    public void testSortByArea_EmptyRepository() {
        List<Circle> sorted = repository.sortByArea(circleService);
        assertTrue(sorted.isEmpty(), "Should return empty list for empty repository");
    }

    @Test
    public void testSortByArea_SortsCorrectly() {
        repository.add(circle1);
        repository.add(circle2);
        repository.add(circle3);
        List<Circle> sorted = repository.sortByArea(circleService);
        assertEquals(sorted.get(0), circle2, "Smallest area should be first");
        assertEquals(sorted.get(1), circle3, "Middle area should be second");
        assertEquals(sorted.get(2), circle1, "Largest area should be last");
    }

    @Test
    public void testSortByArea_WithNullService() {
        repository.add(circle1);
        assertThrows(NullPointerException.class, () -> repository.sortByArea(null));
    }

    @Test
    public void testSortByPerimeter_EmptyRepository() {
        List<Circle> sorted = repository.sortByPerimeter(circleService);
        assertTrue(sorted.isEmpty(), "Should return empty list for empty repository");
    }

    @Test
    public void testSortByPerimeter_SortsCorrectly() {
        repository.add(circle1);
        repository.add(circle2);
        repository.add(circle3);
        List<Circle> sorted = repository.sortByPerimeter(circleService);
        assertEquals(sorted.get(0), circle2, "Smallest perimeter should be first");
        assertEquals(sorted.get(1), circle3, "Middle perimeter should be second");
        assertEquals(sorted.get(2), circle1, "Largest perimeter should be last");
    }

    @Test
    public void testSortByPerimeter_WithNullService() {
        repository.add(circle1);
        assertThrows(NullPointerException.class, () -> repository.sortByPerimeter(null));
    }

    @Test
    public void testSortByXCoordinate_EmptyRepository() {
        List<Circle> sorted = repository.sortByXCoordinate();
        assertTrue(sorted.isEmpty(), "Should return empty list for empty repository");
    }

    @Test
    public void testSortByXCoordinate_SortsCorrectly() {
        repository.add(circle1);
        repository.add(circle2);
        repository.add(circle3);
        List<Circle> sorted = repository.sortByXCoordinate();
        assertEquals(sorted.get(0), circle1, "Smallest x should be first");
        assertEquals(sorted.get(1), circle2, "Middle x should be second");
        assertEquals(sorted.get(2), circle3, "Largest x should be last");
    }

    @Test
    public void testSortByXCoordinate_WithSameXValues() {
        Circle circle4 = new Circle(1.0, 8.0, 5.0);
        repository.add(circle1);
        repository.add(circle4);
        List<Circle> sorted = repository.sortByXCoordinate();
        assertEquals(sorted.size(), 2, "Should return all circles");
    }

    @Test
    public void testSortByYCoordinate_EmptyRepository() {
        List<Circle> sorted = repository.sortByYCoordinate();
        assertTrue(sorted.isEmpty(), "Should return empty list for empty repository");
    }

    @Test
    public void testSortByYCoordinate_SortsCorrectly() {
        repository.add(circle1);
        repository.add(circle2);
        repository.add(circle3);
        List<Circle> sorted = repository.sortByYCoordinate();
        assertEquals(sorted.get(0).getY(), 2.0, "Smallest y should be first");
        assertEquals(sorted.get(1).getY(), 2.0, "Same y should be next");
        assertEquals(sorted.get(2).getY(), 5.0, "Largest y should be last");
    }

    @Test
    public void testSortByYCoordinate_WithSameYValues() {
        Circle circle4 = new Circle(9.0, 2.0, 5.0);
        repository.add(circle1);
        repository.add(circle3);
        repository.add(circle4);
        List<Circle> sorted = repository.sortByYCoordinate();
        assertEquals(sorted.size(), 3, "Should return all circles");
    }
}