package com.serzhputovski.circles.factory.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.serzhputovski.circles.entity.Circle;
import com.serzhputovski.circles.factory.CircleFactory;
import com.serzhputovski.circles.validator.CircleValidator;

import java.util.Optional;

public class CircleFactoryImpl implements CircleFactory {
    private final CircleValidator validator;
    private static final Logger logger = LogManager.getLogger(CircleFactoryImpl.class);;

    public CircleFactoryImpl(CircleValidator validator) {
        this.validator = validator;
    }

    @Override
    public Optional<Circle> create(double x, double y, double radius) {
        if (validator.validateRadius(radius)
            && validator.validateCoordinateX(x)
            && validator.validateCoordinateY(y)
        ) {
            Circle circle = new Circle(x, y, radius);
            logger.info("New circle: {}", circle);
            return Optional.of(circle);
        } else {
            logger.warn("No circle created");
            return Optional.empty();
        }

    }
}