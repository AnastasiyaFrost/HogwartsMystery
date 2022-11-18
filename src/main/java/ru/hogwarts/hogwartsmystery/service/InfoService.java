package ru.hogwarts.hogwartsmystery.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class InfoService {
    Logger logger = LoggerFactory.getLogger(InfoService.class);

    public int getSum() {
        logger.info("Was invoked method for get sum");
        return Stream
                .iterate(1, a -> a +1)
                .limit(1_000_000)
//                .parallel()   *1_784_293_664, прошло времени, мс: 348
                .reduce(0, (a, b) -> a + b ); //1_784_293_664, прошло времени, мс: 160
    }
}
