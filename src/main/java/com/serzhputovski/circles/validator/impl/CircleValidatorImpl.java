package com.serzhputovski.circles.validator.impl;

import com.serzhputovski.circles.validator.CircleValidator;

public class CircleValidatorImpl implements CircleValidator {
    private final double MIN_X_COORDINATE = -5_000_000;
    private final double MIN_Y_COORDINATE = -5_000_000;
    private final double MAX_X_COORDINATE = 5_000_000;
    private final double MAX_Y_COORDINATE = 5_000_000;

    @Override
    public boolean validateRadius(double radius) {
        return radius > 0 ;
    }

    @Override
    public boolean validateCoordinateX(double x){
        return x > MIN_X_COORDINATE && x < MAX_X_COORDINATE;
    }

    @Override
    public boolean validateCoordinateY(double y){
        return y > MIN_Y_COORDINATE && y < MAX_Y_COORDINATE;
    }
}