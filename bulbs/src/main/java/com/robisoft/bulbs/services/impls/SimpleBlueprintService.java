package com.robisoft.bulbs.services.impls;

import com.robisoft.bulbs.models.Blueprint;
import com.robisoft.bulbs.services.BlueprintParser;
import com.robisoft.bulbs.services.BlueprintService;
import com.robisoft.bulbs.services.BulbDistributionCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class SimpleBlueprintService implements BlueprintService {
    @Autowired
    BlueprintParser parser;

    @Autowired
    BulbDistributionCalculator calculator;

    @Override
    public Blueprint solve(Blueprint blueprint) {
        return new Blueprint(calculator.solve(blueprint.getRoom()));
    }

    @Override
    public Blueprint parse(File file) {
        return parser.parse(file);
    }
}
