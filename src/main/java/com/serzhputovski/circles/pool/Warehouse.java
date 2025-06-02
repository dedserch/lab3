package com.serzhputovski.circles.pool;

import com.serzhputovski.circles.entity.Circle;

import java.util.HashMap;
import java.util.Map;

public class Warehouse {
    private static Warehouse instance;
    private final Map<Circle, CircleParameters> parametersMap = new HashMap<>();

    private Warehouse() {}

    public static Warehouse getInstance() {
        if (instance == null) {
            instance = new Warehouse();
        }
        return instance;
    }

    public void put(Circle circle, CircleParameters circleParameters) {
        parametersMap.put(circle, circleParameters);
    }

    public CircleParameters getParameters(Circle circle) {
        return parametersMap.get(circle);
    }

}