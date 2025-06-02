package com.serzhputovski.circles.observer;

public interface CircleObserver<T> {
    void update(T item);
}
