package com.homework.Dev_SpringBoot_HW.config;

import com.homework.Dev_SpringBoot_HW.data.entities.NoteEntity;
import com.homework.Dev_SpringBoot_HW.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthorizationService {
    private final NoteService noteService;

    public boolean isOwnerOfNote(Principal principal, UUID noteId) {
        NoteEntity note = noteService.getById(noteId);
        return principal.getName().equals(note.getUser().getUsername());
    }
}
