package com.serzhputovski.circles.parser.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.serzhputovski.circles.parser.CircleParser;

import java.util.Optional;

public class CircleParserImpl implements CircleParser {
    private static final int EXPECTED_PARTS = 3;
    private static final String DELIMITER = "\\s+";
    private static final Logger logger = LogManager.getLogger(CircleParserImpl.class);

    public Optional<double[]> parse(String line) {
        String[] parts = line.trim().split(DELIMITER);

        if (parts.length != EXPECTED_PARTS) {
            logger.warn("No circles parameters were parsed");
            return Optional.empty();
        }

        double x = Double.parseDouble(parts[0]);
        double y = Double.parseDouble(parts[1]);
        double radius = Double.parseDouble(parts[2]);

        logger.info("Parsed circles parameters {}, {}, {}", x, y, radius);

        return Optional.of(new double[]{x,y,radius});

    }
}
