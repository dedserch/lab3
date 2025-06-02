package com.serzhputovski.circles.observer.impl;

import com.serzhputovski.circles.entity.Circle;
import com.serzhputovski.circles.observer.CircleObserver;
import com.serzhputovski.circles.pool.CircleParameters;
import com.serzhputovski.circles.pool.Warehouse;
import com.serzhputovski.circles.service.CircleService;

public class CircleObserverImpl implements CircleObserver<Circle> {
    private Warehouse warehouse;
    private CircleService circleService;

    public CircleObserverImpl(Warehouse warehouse, CircleService circleService){
        this.warehouse = warehouse;
        this.circleService = circleService;
    }

    @Override
    public void update(Circle item) {
        warehouse.put(item, new CircleParameters(circleService.findCirclePerimeter(item), circleService.findCircleArea(item)));
    }
}
