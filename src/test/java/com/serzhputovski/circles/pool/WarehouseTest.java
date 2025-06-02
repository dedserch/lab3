package com.serzhputovski.circles.pool;

import com.serzhputovski.circles.entity.Circle;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class WarehouseTest {
    private Warehouse warehouse;
    private Circle circle1;
    private Circle circle2;
    private CircleParameters params1;
    private CircleParameters params2;

    @BeforeMethod
    public void setUp() {
        warehouse = Warehouse.getInstance();

        circle1 = new Circle(1.0, 2.0, 3.0);
        circle2 = new Circle(4.0, 5.0, 6.0);
        params1 = new CircleParameters(18.85, 28.27);
        params2 = new CircleParameters(37.70, 113.10);
    }

    @Test
    public void testGetInstance_ReturnsSameInstance() {
        Warehouse firstInstance = Warehouse.getInstance();
        Warehouse secondInstance = Warehouse.getInstance();
        assertSame(firstInstance, secondInstance, "getInstance should return the same instance");
    }

    @Test
    public void testPut_StoresParametersForCircle() {
        warehouse.put(circle1, params1);
        assertEquals(warehouse.getParameters(circle1), params1, "update should store parameters for a circle");
    }

    @Test
    public void testPut_OverwritesExistingParameters() {
        warehouse.put(circle1, params1);
        warehouse.put(circle1, params2);
        assertEquals(warehouse.getParameters(circle1), params2, "update should overwrite existing parameters");
    }

    @Test
    public void testPut_HandlesMultipleCircles() {
        warehouse.put(circle1, params1);
        warehouse.put(circle2, params2);
        assertEquals(warehouse.getParameters(circle1), params1, "update should store parameters for first circle");
        assertEquals(warehouse.getParameters(circle2), params2, "update should store parameters for second circle");
    }

    @Test
    public void testGetParameters_ReturnsCorrectParameters() {
        warehouse.put(circle1, params1);
        CircleParameters retrieved = warehouse.getParameters(circle1);
        assertEquals(retrieved.getPerimeter(), params1.getPerimeter(), 0.01, "getParameters should return correct perimeter");
        assertEquals(retrieved.getArea(), params1.getArea(), 0.01, "getParameters should return correct area");
    }

    @Test
    public void testGetParameters_ReturnsConsistentParametersAfterPut() {
        warehouse.put(circle1, params1);
        CircleParameters firstRetrieval = warehouse.getParameters(circle1);
        CircleParameters secondRetrieval = warehouse.getParameters(circle1);
        assertEquals(firstRetrieval, secondRetrieval, "getParameters should return consistent results");
    }

    @Test
    public void testIntegration_putAndGetWithSameCircleObject() {
        Circle circle = new Circle(1.0, 2.0, 3.0);
        warehouse.put(circle, params1);
        assertEquals(warehouse.getParameters(circle), params1, "Should retrieve what was stored for the same circle object");
    }

    @Test
    public void testIntegration_putAndGetWithEqualCircleObject() {
        Circle circleA = new Circle(1.0, 2.0, 3.0);
        Circle circleB = new Circle(1.0, 2.0, 3.0);
        warehouse.put(circleA, params1);
        assertEquals(warehouse.getParameters(circleB), params1, "Should retrieve parameters for equal circle objects");
    }

    @Test
    public void testIntegration_putAndGetWithDifferentCircleObject() {
        Circle circleA = new Circle(1.0, 2.0, 3.0);
        Circle circleB = new Circle(4.0, 5.0, 6.0);
        warehouse.put(circleA, params1);
        assertNotEquals(warehouse.getParameters(circleB), params1, "Should not retrieve parameters for different circle objects");
    }
}