package com.homework.Dev_SpringBoot_HW.controller.request_entities;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class NoteAddRequest {
    @NotNull
    @Length(min = 1, max = 255)
    private String title;
    @NotNull
    @Length(min = 1, max = 255)
    private String content;
}
