package com.serzhputovski.circles.facade;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.serzhputovski.circles.entity.Circle;
import com.serzhputovski.circles.exception.CircleFileException;
import com.serzhputovski.circles.factory.CircleFactory;
import com.serzhputovski.circles.factory.impl.CircleFactoryImpl;
import com.serzhputovski.circles.parser.impl.CircleParserImpl;
import com.serzhputovski.circles.reader.impl.CircleFileReaderImpl;
import com.serzhputovski.circles.service.CircleService;
import com.serzhputovski.circles.service.impl.CircleServiceImpl;
import com.serzhputovski.circles.specification.impl.AreaRangeCircleSpecification;
import com.serzhputovski.circles.validator.CircleValidator;
import com.serzhputovski.circles.validator.impl.CircleValidatorImpl;
import com.serzhputovski.circles.repository.CircleRepository;
import com.serzhputovski.circles.repository.impl.CircleRepositoryImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Facade {
    private final CircleValidator validator;
    private final CircleFactory factory;
    private final CircleParserImpl circleParser;
    private final CircleFileReaderImpl circleFileReader;
    private final CircleService circleService;
    private final CircleRepository circleRepository;
    private static final Logger logger = LogManager.getLogger(Facade.class);

    public Facade() {
        this.validator = new CircleValidatorImpl();
        this.factory = new CircleFactoryImpl(validator);
        this.circleParser = new CircleParserImpl();
        this.circleFileReader = new CircleFileReaderImpl();
        this.circleService = new CircleServiceImpl();
        this.circleRepository = new CircleRepositoryImpl();
    }

    public void execute(String filePath) throws CircleFileException {
        List<String> circleStrings = circleFileReader.readFile(filePath);

        ArrayList<Circle> circles = new ArrayList<>();

        circleStrings.forEach(circleString -> {
            Optional<double[]> circleParameters = circleParser.parse(circleString);
            if (circleParameters.isPresent()){
                double[] parameters = circleParameters.get();
                Optional<Circle> circle = factory.create(parameters[0], parameters[1], parameters[2]);
                circle.ifPresent(c -> {
                    circles.add(c);
                    circleRepository.add(c);
                });
            }
        });

        circles.forEach(logger::info);


        circleRepository.query(new AreaRangeCircleSpecification(0, 150));

        ArrayList<ArrayList<Circle>> lines = circleService.findCirclesOnSameLine(circles);

        lines.forEach(line -> logger.info("Group of circles on line:\n {}", line));

        ArrayList<ArrayList<Circle>> groups = circleService.findCirclesOnSameLine(circles);
        groups.forEach(group -> logger.info("Group of circles with similar property:\n {}", group));
    }
}