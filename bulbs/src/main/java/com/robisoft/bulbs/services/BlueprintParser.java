package com.robisoft.bulbs.services;

import com.robisoft.bulbs.models.Blueprint;
import org.springframework.stereotype.Service;

import java.io.File;

public interface BlueprintParser {
    public Blueprint parse(File blueprint);
}
