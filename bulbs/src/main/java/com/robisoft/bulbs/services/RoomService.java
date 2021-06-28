package com.robisoft.bulbs.services;

import com.robisoft.bulbs.models.Room;

import java.io.File;

public interface RoomService {
    public Room solve(Room room);
    public Room parse(File file);
}
