package com.homework.Dev_SpringBoot_HW.data.repositories;
import com.homework.Dev_SpringBoot_HW.data.entities.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface NoteRepository extends JpaRepository<NoteEntity, UUID>, JpaSpecificationExecutor<NoteEntity> {
    @Query(nativeQuery = true, value =
            "SELECT id, title, content, user_id FROM notes WHERE user_id = :username")
    List<NoteEntity> findAllByUserId(@Param("username") String userId);
}
