package com.homework.Dev_SpringBoot_HW.data.repositories;

import com.homework.Dev_SpringBoot_HW.data.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
}
