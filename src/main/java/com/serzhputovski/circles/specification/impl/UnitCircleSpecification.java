package com.serzhputovski.circles.specification.impl;

import com.serzhputovski.circles.entity.Circle;
import com.serzhputovski.circles.specification.Specification;

public class UnitCircleSpecification implements Specification<Circle> {

    @Override
    public boolean isSatisfiedBy(Circle circle){
        return circle.getRadius() == 1;
    }
}
