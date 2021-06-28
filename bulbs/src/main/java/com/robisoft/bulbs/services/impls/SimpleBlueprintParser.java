package com.robisoft.bulbs.services.impls;

import com.robisoft.bulbs.models.Blueprint;
import com.robisoft.bulbs.services.BlueprintParser;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Service
public class SimpleBlueprintParser implements BlueprintParser {
    @Override
    public Blueprint parse(File blueprint) {
        try {
            int[][] room = Files.lines(blueprint.toPath())
                    .filter(line -> !line.equals(""))
                    .map(line -> line.chars()
                            .filter(letter -> (char) letter != ' ')
                    .map(Character::getNumericValue)
                    .toArray())
                    .toArray(int[][]::new);

            return new Blueprint(room);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Blueprint(new int[0][0]);
    }
}
