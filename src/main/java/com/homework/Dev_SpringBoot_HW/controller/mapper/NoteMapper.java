package com.homework.Dev_SpringBoot_HW.controller.mapper;

import com.homework.Dev_SpringBoot_HW.controller.dto.NoteDto;
import com.homework.Dev_SpringBoot_HW.controller.request_entities.NoteAddRequest;
import com.homework.Dev_SpringBoot_HW.data.entities.NoteEntity;
import com.homework.Dev_SpringBoot_HW.data.entities.UserEntity;
import com.homework.Dev_SpringBoot_HW.service.UserService;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class NoteMapper {
    private final EntityManager entityManager;

    public NoteEntity mapDtoToEntity(NoteDto noteDto, Principal principal){
        String username = principal.getName();
        UserEntity userReference = entityManager.getReference(UserEntity.class, username);
        return new NoteEntity(noteDto.getId(), noteDto.getTitle(), noteDto.getContent(), userReference);
    }
    public NoteDto mapEntityToDto(NoteEntity noteEntity){
        return new NoteDto(noteEntity.getId(), noteEntity.getTitle(), noteEntity.getContent());
    }
    public NoteDto mapNoteAddRequestToDto(NoteAddRequest noteAddRequest){
        return new NoteDto (UUID.randomUUID(), noteAddRequest.getTitle(), noteAddRequest.getContent());
    }
    public List<NoteDto> mapAllEntitiesToDto(List<NoteEntity> entities){
        List<NoteDto> notesDto = new LinkedList<>();
        for(NoteEntity note: entities){
            notesDto.add(mapEntityToDto(note));
        }
        return notesDto;
    }
}
