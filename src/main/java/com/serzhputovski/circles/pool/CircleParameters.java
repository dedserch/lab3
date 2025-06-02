package com.serzhputovski.circles.pool;

public class CircleParameters {
    private double perimeter;
    private double area;

    public CircleParameters(double perimeter, double area) {
        this.perimeter = perimeter;
        this.area = area;
    }

    public double getPerimeter() {
        return perimeter;
    }

    public void setPerimeter(double perimeter) {
        this.perimeter = perimeter;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CircleParameters(perimeter=");
        sb.append(perimeter);
        sb.append(", area=");
        sb.append(area);
        sb.append("}");
        return sb.toString();
    }
}