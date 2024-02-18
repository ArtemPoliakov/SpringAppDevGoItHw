package com.homework.Dev_SpringBoot_HW.crud_services;

import com.homework.Dev_SpringBoot_HW.TempDb;
import com.homework.Dev_SpringBoot_HW.entities.Note;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class NoteCrudService {
    HashMap<UUID, Note> dataMap = TempDb.getInstance().getDataMap();
    public List<Note> listAll(){
        return dataMap.values().stream().collect(Collectors.toList());
    }
    public Note add(Note note){
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
    public void update(Note note){
        if(dataMap.containsKey(note.getId())){
            dataMap.get(note.getId()).setTitle(note.getTitle());
            dataMap.get(note.getId()).setContent(note.getContent());
        } else{
            throw new NoSuchElementException();
        }
    }
    public Note getById(UUID id){
        if(dataMap.containsKey(id)){
            return dataMap.get(id);
        } else{
            throw new NoSuchElementException();
        }
    }
}
