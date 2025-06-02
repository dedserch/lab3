package com.serzhputovski.circles.observer;

public interface Observable {
    void addObserver(CircleObserver observer);
    void removeObserver(CircleObserver observer);
    void notifyObservers();
}
