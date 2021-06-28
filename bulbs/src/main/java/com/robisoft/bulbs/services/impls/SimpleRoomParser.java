package com.robisoft.bulbs.services.impls;

import com.robisoft.bulbs.models.Room;
import com.robisoft.bulbs.services.RoomParser;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Service
public class SimpleRoomParser implements RoomParser {
    @Override
    public Room parse(File blueprint) {
        try {
            int[][] room = Files.lines(blueprint.toPath())
                    .filter(line -> !line.equals(""))
                    .map(line -> line.chars()
                            .filter(letter -> (char) letter != ' ')
                    .map(Character::getNumericValue)
                    .toArray())
                    .toArray(int[][]::new);

            return new Room(room);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Room(new int[0][0]);
    }
}
