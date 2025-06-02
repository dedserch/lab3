package com.serzhputovski.circles.comparator.impl;

import com.serzhputovski.circles.comparator.CircleComparator;
import com.serzhputovski.circles.entity.Circle;

public class CircleXCoordinateComparatorImpl implements CircleComparator {
    @Override
    public int compare(Circle o1, Circle o2) {
        return Double.compare(o1.getX(), o2.getX());
    }
}
