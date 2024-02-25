package com.homework.Dev_SpringBoot_HW.controller.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;
@Data
@AllArgsConstructor
public class NoteDto {
    @NotNull
    private UUID id;
    @NotNull
    @Length(min = 1, max = 255)
    private String title;
    @Length(min = 1, max = 500)
    @NotNull
    private String content;
}
