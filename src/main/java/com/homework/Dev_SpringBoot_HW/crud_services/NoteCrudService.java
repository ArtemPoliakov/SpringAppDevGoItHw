package com.homework.Dev_SpringBoot_HW.crud_services;

import com.homework.Dev_SpringBoot_HW.data.TempDb;
import com.homework.Dev_SpringBoot_HW.data.entities.NoteEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class NoteCrudService {
    HashMap<UUID, NoteEntity> dataMap = TempDb.getInstance().getDataMap();
    public List<NoteEntity> listAll(){
        return dataMap.values().stream().toList();
    }
    public NoteEntity add(NoteEntity note){
        note.setId(UUID.randomUUID());
        dataMap.put(note.getId(), note);
        return note;
    }
    public void deleteById(UUID id){
        if(dataMap.containsKey(id)){
            dataMap.remove(id);
        } else{
            throw new NoSuchElementException();
        }
    }
    public void update(NoteEntity note){
        if(dataMap.containsKey(note.getId())){
            dataMap.get(note.getId()).setTitle(note.getTitle());
            dataMap.get(note.getId()).setContent(note.getContent());
        } else{
            throw new NoSuchElementException();
        }
    }
    public NoteEntity getById(UUID id){
        if(dataMap.containsKey(id)){
            return dataMap.get(id);
        } else{
            throw new NoSuchElementException();
        }
    }
}
