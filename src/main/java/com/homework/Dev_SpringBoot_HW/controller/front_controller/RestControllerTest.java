package com.homework.Dev_SpringBoot_HW.controller.front_controller;

import com.homework.Dev_SpringBoot_HW.controller.dto.NoteDto;
import com.homework.Dev_SpringBoot_HW.controller.mapper.NoteMapper;
import com.homework.Dev_SpringBoot_HW.controller.request_entities.NoteAddRequest;
import com.homework.Dev_SpringBoot_HW.service.NoteCrudService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@Valid
@RequestMapping("/rest")
@RequiredArgsConstructor
public class RestControllerTest {
    private static final String REDIRECT_TO_PRIMARY = "redirect:/rest/list";
    private static final String ADD_URL = "/add";
    private static final String LIST_URL = "/list";

    private final NoteCrudService noteCrudService;
    private final NoteMapper noteMapper;

    @GetMapping(LIST_URL)
    public ResponseEntity<List<NoteDto>> listAllNotes(HttpServletResponse servletResponse){
        List<NoteDto> noteEntities = noteMapper.mapAllNoteEntitiesToDto(noteCrudService.listAll());
        ResponseEntity<List<NoteDto>> response =
                new ResponseEntity<>(noteEntities, HttpStatus.OK);
        Cookie cookie = new Cookie("HelloCookie","HelloFromArtem!");
        servletResponse.addCookie(cookie);
        return response;
    }

    @PostMapping(ADD_URL)
    public String addNote(
            @Valid @RequestBody NoteAddRequest noteAddRequest, HttpServletRequest req)
    {
        Cookie cookie1 = Arrays.stream(req.getCookies())
                .filter(cookie -> cookie.getName().equals("HelloCookie"))
                .findAny()
                .get();
        System.out.println(cookie1);

        NoteDto noteDto = noteMapper.mapNoteAddRequestToDto(noteAddRequest);
        noteCrudService.add(noteMapper.mapDtoToEntity(noteDto));
        return REDIRECT_TO_PRIMARY;
    }
}
