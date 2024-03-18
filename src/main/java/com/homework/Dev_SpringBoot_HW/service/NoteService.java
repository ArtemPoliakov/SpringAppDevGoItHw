package com.homework.Dev_SpringBoot_HW.service;

import com.homework.Dev_SpringBoot_HW.data.entities.NoteEntity;
import com.homework.Dev_SpringBoot_HW.data.repositories.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;

    public List<NoteEntity> listAll() {
        return noteRepository.findAll();
    }

    public List<NoteEntity> findAllForUser(String name){
        return noteRepository.findAllByUserId(name);
    }
    public NoteEntity add(NoteEntity note) {
        return noteRepository.save(note);
    }

    public void deleteById(UUID id) {
        noteRepository.deleteById(id);
    }

    public void update(NoteEntity note) {
        noteRepository.save(note);
    }

    public NoteEntity getById(UUID id) {
        return noteRepository
                .findById(id)
                .orElseGet(() -> new NoteEntity(null, "UNDEFINED", "UNDEFINED", null));
    }

    //TEST
    public List<NoteEntity> findAllWithLengthLessThan(int maxTitleLength, int maxContentLength) {
        return noteRepository.findAll((root, cq, cb) ->
                cb.and(
                        cb.lessThan(cb.length(root.get("title")), maxTitleLength),
                        cb.lessThan(cb.length(root.get("content")), maxContentLength)
                ));
    }
}
