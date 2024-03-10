package com.homework.Dev_SpringBoot_HW.data.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="notes")
public class NoteEntity {
    @Id
    private UUID id;
    private String title;
    private String content;
}
