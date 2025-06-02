package com.serzhputovski.circles.specification.impl;

import com.serzhputovski.circles.entity.Circle;
import com.serzhputovski.circles.pool.Warehouse;
import com.serzhputovski.circles.specification.Specification;

public class PerimeterRangeCircleSpecification implements Specification<Circle> {
    private double minRange;
    private double maxRange;
    private static final Warehouse warehouse = Warehouse.getInstance();


    public PerimeterRangeCircleSpecification(double minRange, double maxRange){
        this.minRange = minRange;
        this.maxRange = maxRange;
    }

    @Override
    public boolean isSatisfiedBy(Circle circle){
        double perimeter = warehouse.getParameters(circle).getPerimeter();
        return perimeter >= minRange && perimeter <= maxRange;
    }
}
