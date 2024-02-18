package com.homework.Dev_SpringBoot_HW;

import com.homework.Dev_SpringBoot_HW.entities.Note;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.UUID;

@Component
public class TempDb {
    private static final TempDb INSTANCE = new TempDb();
    private final HashMap<UUID, Note> DATA_MAP = new HashMap<>();
    private TempDb(){}
    public static TempDb getInstance(){
        return INSTANCE;
    }
    public HashMap<UUID, Note> getDataMap(){
        return DATA_MAP;
    }
}
