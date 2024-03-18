package com.homework.Dev_SpringBoot_HW.data.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Entity
@Table(name="users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    public UserEntity(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Id
    private String username;
    private String password;
    @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL})
    private List<NoteEntity> notes;
}
