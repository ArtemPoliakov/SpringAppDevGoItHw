package com.homework.Dev_SpringBoot_HW.controller.front_controller;

import com.homework.Dev_SpringBoot_HW.controller.dto.NoteDto;
import com.homework.Dev_SpringBoot_HW.controller.mapper.NoteMapper;
import com.homework.Dev_SpringBoot_HW.controller.request_entities.NoteAddRequest;
import com.homework.Dev_SpringBoot_HW.service.NoteService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Controller
@Validated
@RequestMapping("/note")
@RequiredArgsConstructor
public class NoteFrontController {
    private static final String LIST_URL = "/list";
    private static final String EDIT_URL = "/edit";
    private static final String ADD_URL = "/add";
    private static final String DELETE_URL = "/delete";
    private static final String PRIMARY_TEMPLATE_NAME = "primary_page";
    private static final String EDIT_TEMPLATE_NAME = "edit_note_page";
    private static final String ADD_TEMPLATE_NAME = "add_note_page";
    private static final String REDIRECT_TO_PRIMARY = "redirect:/note/list";

    private final NoteService noteService;
    private final NoteMapper noteMapper;

    @GetMapping(LIST_URL)
    public ModelAndView listAllNotes(){
        ModelAndView modelAndView = new ModelAndView(PRIMARY_TEMPLATE_NAME);
        modelAndView.addObject("notes",
                noteMapper.mapAllNoteEntitiesToDto(noteService.listAll()));
        modelAndView.setStatus(HttpStatus.OK);
        return modelAndView;
    }

    @GetMapping(EDIT_URL)
    public ModelAndView getUpdatePageForNote(
            @RequestParam(name = "id") @NotNull UUID id)
    {
        ModelAndView modelAndView = new ModelAndView(EDIT_TEMPLATE_NAME);
        modelAndView.addObject("note",
                noteMapper.mapEntityToDto(noteService.getById(id)));
        modelAndView.setStatus(HttpStatus.OK);
        return modelAndView;
    }

    @PostMapping(EDIT_URL)
    public String updateNoteById(
            @Valid @ModelAttribute("editNote") NoteAddRequest noteAddRequest,
            @RequestParam("id") @NotNull UUID id)
    {
        NoteDto noteDto = new NoteDto(id, noteAddRequest.getTitle(), noteAddRequest.getContent());
        noteService.update(noteMapper.mapDtoToEntity(noteDto));
        return REDIRECT_TO_PRIMARY;
    }

    @GetMapping(ADD_URL)
    public ModelAndView getPageForAddingNote(){
        ModelAndView modelAndView = new ModelAndView(ADD_TEMPLATE_NAME);
        modelAndView.setStatus(HttpStatus.OK);
        return modelAndView;
    }

    @PostMapping(ADD_URL)
    public String addNote(
           @Valid @ModelAttribute("addNote") NoteAddRequest noteAddRequest)
    {
        NoteDto noteDto = noteMapper.mapNoteAddRequestToDto(noteAddRequest);
        noteService.add(noteMapper.mapDtoToEntity(noteDto));
        return REDIRECT_TO_PRIMARY;
    }

    @PostMapping(DELETE_URL)
    public String deleteNote(@RequestParam("id") @NotNull UUID id){
        noteService.deleteById(id);
        return REDIRECT_TO_PRIMARY;
    }

    //TEST
    @GetMapping("/specTestLessThan")
    public ModelAndView specTestLessThan(@RequestParam(name="mtl") int mtl, @RequestParam(name="mcl") int mcl){
        ModelAndView modelAndView = new ModelAndView(PRIMARY_TEMPLATE_NAME);
        modelAndView.addObject("notes",
                noteMapper.mapAllNoteEntitiesToDto(noteService.findAllWithLengthLessThan(mtl, mcl)));
        modelAndView.setStatus(HttpStatus.OK);
        return modelAndView;
    }
}