package com.serzhputovski.circles.reader;

import com.serzhputovski.circles.exception.CircleFileException;

import java.util.List;

public interface CircleFileReader {
    public List<String> readFile(String path) throws CircleFileException;
}
