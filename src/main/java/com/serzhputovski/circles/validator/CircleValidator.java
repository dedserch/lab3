package com.serzhputovski.circles.validator;

public interface CircleValidator {

    boolean validateRadius(double radius);

    boolean validateCoordinateX(double x);

    boolean validateCoordinateY(double y);

}