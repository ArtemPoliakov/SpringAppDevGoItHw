package com.homework.Dev_SpringBoot_HW.data.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;
@Data
@AllArgsConstructor
public class NoteEntity {
    private UUID id;
    private String title;
    private String content;
}
