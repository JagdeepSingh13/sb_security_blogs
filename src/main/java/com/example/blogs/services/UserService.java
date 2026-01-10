package com.example.blogs.services;

import com.example.blogs.domain.entities.User;

import java.util.UUID;

public interface UserService {

    User getUserById(UUID id);

}
