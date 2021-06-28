package com.robisoft.bulbs.services;

import com.robisoft.bulbs.models.Room;

import java.io.File;

public interface RoomParser {
    public Room parse(File blueprint);
}
