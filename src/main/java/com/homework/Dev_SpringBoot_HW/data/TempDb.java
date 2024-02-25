package com.homework.Dev_SpringBoot_HW.data;

import com.homework.Dev_SpringBoot_HW.data.entities.NoteEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.UUID;

@Component
public class TempDb {
    private static final TempDb INSTANCE = new TempDb();
    private final HashMap<UUID, NoteEntity> DATA_MAP = new HashMap<>();
    private TempDb(){}
    public static TempDb getInstance(){
        return INSTANCE;
    }
    public HashMap<UUID, NoteEntity> getDataMap(){
        return DATA_MAP;
    }
}
