package com.example.blogs.services.impl;

import com.example.blogs.domain.entities.User;
import com.example.blogs.repos.UserRepo;
import com.example.blogs.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Override
    public User getUserById(UUID id) {
        return userRepo.findById(id)
                .orElseThrow(()->new EntityNotFoundException("user not found"));
    }
}
