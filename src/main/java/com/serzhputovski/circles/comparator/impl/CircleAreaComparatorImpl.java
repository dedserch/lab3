package com.serzhputovski.circles.comparator.impl;

import com.serzhputovski.circles.comparator.CircleComparator;
import com.serzhputovski.circles.entity.Circle;
import com.serzhputovski.circles.pool.Warehouse;

public class CircleAreaComparatorImpl implements CircleComparator {
    private final Warehouse warehouse = Warehouse.getInstance();

    @Override
    public int compare(Circle o1, Circle o2) {
        double area1 = warehouse.getParameters(o1).getArea();
        double area2 = warehouse.getParameters(o2).getArea();

        return Double.compare(area1, area2);
    }
}
