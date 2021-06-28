package com.robisoft.bulbs.services.impls;

import com.robisoft.bulbs.models.Room;
import com.robisoft.bulbs.services.RoomParser;
import com.robisoft.bulbs.services.RoomService;
import com.robisoft.bulbs.services.BulbDistributionCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class SimpleRoomService implements RoomService {
    @Autowired
    RoomParser parser;

    @Autowired
    BulbDistributionCalculator calculator;

    @Override
    public Room solve(Room room) {
        return new Room(calculator.solve(room.getBlueprint()));
    }

    @Override
    public Room parse(File file) {
        return parser.parse(file);
    }
}
