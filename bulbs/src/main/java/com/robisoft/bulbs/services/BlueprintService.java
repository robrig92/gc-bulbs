package com.robisoft.bulbs.services;

import com.robisoft.bulbs.models.Blueprint;

import java.io.File;

public interface BlueprintService {
    public Blueprint solve(Blueprint blueprint);
    public Blueprint parse(File file);
}
