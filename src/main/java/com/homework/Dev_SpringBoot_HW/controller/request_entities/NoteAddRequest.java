package com.homework.Dev_SpringBoot_HW.controller.request_entities;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data

public class NoteAddRequest {
    @NotNull
    @Size(min = 3, max = 255)
    private String title;
    @NotNull
    @Size(min = 3, max = 255)
    private String content;
}
