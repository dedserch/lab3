package com.serzhputovski.circles.service.impl;

import com.serzhputovski.circles.entity.Circle;
import com.serzhputovski.circles.service.CircleService;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class CircleServiceImpl implements CircleService {
    private final double LARGE_CIRCLE_MIN_RADIUS = 4000;

    public Optional<Circle> findCircleWithMaxArea(List<Circle> circles) {
        return circles.stream()
                .max(Comparator.comparing(this::findCircleArea));
    }
    
    public Optional<Circle> findCircleWithMinArea(List<Circle> circles) {
        return circles.stream()
                .min(Comparator.comparing(this::findCircleArea));
    }
    
    public Optional<Circle> findCircleWithMaxPerimeter(List<Circle> circles) {
        return circles.stream()
                .max(Comparator.comparing(this::findCirclePerimeter));
    }
    
    public Optional<Circle> findCircleWithMinPerimeter(List<Circle> circles) {
        return circles.stream()
                .min(Comparator.comparing(this::findCirclePerimeter));
    }

    public ArrayList<ArrayList<Circle>> findCirclesOnSameLine(List<Circle> circles) {
        ArrayList<ArrayList<Circle>> result = new ArrayList<>();
        boolean[][] processed = new boolean[circles.size()][circles.size()];

        for (int i = 0; i < circles.size(); i++) {
            for (int j = i + 1; j < circles.size(); j++) {
                if (processed[i][j]) {
                    continue;
                }

                Circle c1 = circles.get(i);
                Circle c2 = circles.get(j);

                if (c1.getX() == c2.getX() && c1.getY() == c2.getY()) {
                    continue;
                }

                ArrayList<Circle> circlesOnLine = new ArrayList<>();
                circlesOnLine.add(c1);
                circlesOnLine.add(c2);

                double a, b, c;

                if (c1.getX() == c2.getX()) {
                    a = 1;
                    b = 0;
                    c = -c1.getX();
                } else {
                    a = (c2.getY() - c1.getY()) / (c1.getX() - c2.getX());
                    b = 1.0;
                    c = -(a * c1.getX() + b * c1.getY());
                }

                for (int k = 0; k < circles.size(); k++) {
                    if (k != i && k != j) {
                        Circle c3 = circles.get(k);
                        double distance = Math.abs(a * c3.getX() + b * c3.getY() + c) / Math.sqrt(a * a + b * b);
                        if (Math.abs(distance) < 1e-10) {
                            circlesOnLine.add(c3);

                            for (int l = 0; l < circlesOnLine.size() - 1; l++) {
                                int index = circles.indexOf(circlesOnLine.get(l));
                                processed[Math.min(index, k)][Math.max(index, k)] = true;
                            }
                        }
                    }
                }

                if (circlesOnLine.size() >= 2) {
                    if (a == 1 && b == 0) {
                        circlesOnLine.sort(Comparator.comparing(Circle::getY));
                    } else {
                        circlesOnLine.sort(Comparator.comparing(Circle::getX));
                    }

                    result.add(circlesOnLine);
                }
            }
        }

        return result;
    }

    @Override
    public double findCircleArea(Circle circle) {
        return Math.PI * circle.getRadius() * circle.getRadius();
    }

    @Override
    public double findCirclePerimeter(Circle circle) {
        return 2 * Math.PI * circle.getRadius();
    }

    @Override
    public boolean isUnitCircle(Circle circle) {
        return circle.getRadius() == 1;
    }

    @Override
    public boolean isLargeCircle(Circle circle) {
        return circle.getRadius() >= LARGE_CIRCLE_MIN_RADIUS;
    }
}