package com.serzhputovski.circles.specification;

public interface Specification<T> {
    boolean isSatisfiedBy(T item);
}