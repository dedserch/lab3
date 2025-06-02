package com.serzhputovski.circles.repository;

import com.serzhputovski.circles.comparator.CircleComparator;
import com.serzhputovski.circles.entity.Circle;
import com.serzhputovski.circles.specification.Specification;

import java.util.List;

public interface CircleRepository {
    void add(Circle circle);
    List<Circle> sort(CircleComparator comparator);
    List<Circle> query(Specification<Circle> specification);
    List<Circle> all();
}