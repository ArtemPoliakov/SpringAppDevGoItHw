package com.homework.Dev_SpringBoot_HW.service;

import com.homework.Dev_SpringBoot_HW.data.entities.UserEntity;
import com.homework.Dev_SpringBoot_HW.data.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<UserEntity> findALl(){
        return userRepository.findAll();
    }
    public UserEntity findById(String id){
        return userRepository.findById(id).orElseThrow();
    }
    public boolean save(UserEntity user){
        try{
            userRepository.save(user);
        }catch(Exception e){
            return false;
        }
        return true;
    }

    public boolean update(UserEntity user){
        try{
            userRepository.save(user);
        }catch(Exception e){
            return false;
        }
        return true;
    }

    public boolean deleteById(String id){
        try{
            userRepository.deleteById(id);
        }catch(Exception e){
            return false;
        }
        return true;
    }
}
