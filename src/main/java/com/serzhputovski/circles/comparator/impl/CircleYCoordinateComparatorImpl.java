package com.serzhputovski.circles.comparator.impl;

import com.serzhputovski.circles.comparator.CircleComparator;
import com.serzhputovski.circles.entity.Circle;

public class CircleYCoordinateComparatorImpl implements CircleComparator {
    @Override
    public int compare(Circle o1, Circle o2) {
        return Double.compare(o1.getY(), o2.getY());
    }
}
