package com.homework.Dev_SpringBoot_HW.entities;

import lombok.Data;

import java.util.UUID;
@Data
public class Note {
    private UUID id;
    private String title;
    private String content;
}
