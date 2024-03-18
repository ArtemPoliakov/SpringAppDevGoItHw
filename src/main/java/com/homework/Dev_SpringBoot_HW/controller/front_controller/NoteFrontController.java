package com.homework.Dev_SpringBoot_HW.controller.front_controller;

import com.homework.Dev_SpringBoot_HW.config.AuthorizationService;
import com.homework.Dev_SpringBoot_HW.controller.dto.NoteDto;
import com.homework.Dev_SpringBoot_HW.controller.mapper.NoteMapper;
import com.homework.Dev_SpringBoot_HW.controller.request_entities.NoteAddRequest;
import com.homework.Dev_SpringBoot_HW.data.entities.NoteEntity;
import com.homework.Dev_SpringBoot_HW.data.entities.UserEntity;
import com.homework.Dev_SpringBoot_HW.service.NoteService;
import com.homework.Dev_SpringBoot_HW.service.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;
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
    private static final String FORBIDDEN_URL = "/forbidden";
    private static final String PRIMARY_TEMPLATE_NAME = "primary_page";
    private static final String EDIT_TEMPLATE_NAME = "edit_note_page";
    private static final String ADD_TEMPLATE_NAME = "add_note_page";
    private static final String FORBIDDEN_TEMPLATE_NAME = "forbidden";
    private static final String REDIRECT_TO_PRIMARY = "redirect:/note/list";
    private static final String REDIRECT_TO_FORBIDDEN = "redirect:/note/forbidden";

    private final NoteService noteService;
    private final NoteMapper noteMapper;
    private final AuthorizationService authService;
    private final UserService userService;
    @PostConstruct
    public void init(){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        userService.save(new UserEntity("admin", encoder.encode("password")));
        userService.save(new UserEntity("admin2", encoder.encode("password2")));
    }

    @GetMapping(LIST_URL)
    public ModelAndView listAllNotesForUser(Principal principal){
        ModelAndView modelAndView = new ModelAndView(PRIMARY_TEMPLATE_NAME);
        List<NoteEntity> allForUser = noteService.findAllForUser(principal.getName());
        modelAndView.addObject("notes", noteMapper.mapAllEntitiesToDto(allForUser));
        modelAndView.setStatus(HttpStatus.OK);
        return modelAndView;
    }

    @GetMapping(EDIT_URL)
    public ModelAndView getUpdatePageForNote(
            @RequestParam(name = "id") @NotNull UUID id, Principal principal)
    {
        if(authService.isOwnerOfNote(principal, id)){
            ModelAndView successModelAndView = new ModelAndView(EDIT_TEMPLATE_NAME);
            successModelAndView.addObject("note",
                    noteMapper.mapEntityToDto(noteService.getById(id)));
            successModelAndView.setStatus(HttpStatus.OK);
            return successModelAndView;
        } else{
            ModelAndView forbiddenModelAndView = new ModelAndView(FORBIDDEN_TEMPLATE_NAME);
            forbiddenModelAndView.setStatus(HttpStatus.FORBIDDEN);
            return forbiddenModelAndView;
        }
    }

    @PostMapping(EDIT_URL)
    public String updateNoteById(
            @Valid @ModelAttribute("editNote") NoteAddRequest noteAddRequest,
            @RequestParam("id") @NotNull UUID id, Principal principal)
    {
        if(authService.isOwnerOfNote(principal, id)){
            NoteDto noteDto = new NoteDto(id, noteAddRequest.getTitle(), noteAddRequest.getContent());
            noteService.update(noteMapper.mapDtoToEntity(noteDto, principal));
            return REDIRECT_TO_PRIMARY;
        } else{
            return REDIRECT_TO_FORBIDDEN;
        }
    }

    @GetMapping(ADD_URL)
    public ModelAndView getPageForAddingNote(){
        ModelAndView modelAndView = new ModelAndView(ADD_TEMPLATE_NAME);
        modelAndView.setStatus(HttpStatus.OK);
        return modelAndView;
    }

    @PostMapping(ADD_URL)
    public String addNote(
           @Valid @ModelAttribute("addNote") NoteAddRequest noteAddRequest, Principal principal)
    {
        NoteDto noteDto = noteMapper.mapNoteAddRequestToDto(noteAddRequest);
        noteService.add(noteMapper.mapDtoToEntity(noteDto, principal));
        return REDIRECT_TO_PRIMARY;
    }

    @PostMapping(DELETE_URL)
    public String deleteNote(@RequestParam("id") @NotNull UUID id, Principal principal){
        if(authService.isOwnerOfNote(principal, id)){
            noteService.deleteById(id);
            return REDIRECT_TO_PRIMARY;
        } else{
            return REDIRECT_TO_FORBIDDEN;
        }
    }

    @GetMapping(FORBIDDEN_URL)
    public ModelAndView getForbiddenPage(){
        ModelAndView modelAndView = new ModelAndView(FORBIDDEN_TEMPLATE_NAME);
        modelAndView.setStatus(HttpStatus.FORBIDDEN);
        return modelAndView;
    }

    //TEST
    @GetMapping("/specTestLessThan")
    public ModelAndView specTestLessThan(@RequestParam(name="mtl") int mtl, @RequestParam(name="mcl") int mcl){
        ModelAndView modelAndView = new ModelAndView(PRIMARY_TEMPLATE_NAME);
        modelAndView.addObject("notes",
                noteMapper.mapAllEntitiesToDto(noteService.findAllWithLengthLessThan(mtl, mcl)));
        modelAndView.setStatus(HttpStatus.OK);
        return modelAndView;
    }
}