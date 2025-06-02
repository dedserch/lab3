package com.serzhputovski.circles.specification.impl;

import com.serzhputovski.circles.entity.Circle;
import com.serzhputovski.circles.service.CircleService;
import com.serzhputovski.circles.specification.Specification;

public class LargeCircleSpecification implements Specification<Circle> {
    private CircleService circleService;

    public LargeCircleSpecification(CircleService circleService){
        this.circleService = circleService;
    }

    @Override
    public boolean isSatisfiedBy(Circle circle){
        return circleService.isLargeCircle(circle);
    }
}
