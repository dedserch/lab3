package com.serzhputovski.circles.parser;

import java.util.Optional;

public interface CircleParser {
    Optional<double[]> parse(String line);
}
