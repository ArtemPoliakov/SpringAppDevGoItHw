package com.homework.Dev_SpringBoot_HW.controller.mapper;

import com.homework.Dev_SpringBoot_HW.controller.dto.NoteDto;
import com.homework.Dev_SpringBoot_HW.controller.request_entities.NoteAddRequest;
import com.homework.Dev_SpringBoot_HW.data.entities.NoteEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class NoteMapper {
    public NoteEntity mapDtoToEntity(NoteDto noteDto){
        return new NoteEntity(noteDto.getId(), noteDto.getTitle(), noteDto.getContent());
    }
    public List<NoteEntity> mapAllNoteDtoToEntities(List<NoteDto> noteDtoList){
        return noteDtoList.stream().map(d -> new NoteMapper().mapDtoToEntity(d)).toList();
    }
    public NoteDto mapEntityToDto(NoteEntity noteEntity){
        return new NoteDto(noteEntity.getId(), noteEntity.getTitle(), noteEntity.getContent());
    }
    public List<NoteDto> mapAllNoteEntitiesToDto(List<NoteEntity> noteEntities){
        return noteEntities.stream().map(e -> new NoteMapper().mapEntityToDto(e)).toList();
    }
    public NoteDto mapNoteAddRequestToDto(NoteAddRequest noteAddRequest){
        return new NoteDto (UUID.randomUUID(), noteAddRequest.getTitle(), noteAddRequest.getContent());
    }
    public List<NoteDto> mapAllNoteAddRequestsToDto(List<NoteAddRequest> noteAddRequests){
        return noteAddRequests.stream().map(r -> new NoteMapper().mapNoteAddRequestToDto(r)).toList();
    }
}
