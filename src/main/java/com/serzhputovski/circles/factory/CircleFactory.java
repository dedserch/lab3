package com.serzhputovski.circles.factory;

import com.serzhputovski.circles.entity.Circle;

import java.util.Optional;

public interface CircleFactory {
    Optional<Circle> create(double x, double y, double radius);
}