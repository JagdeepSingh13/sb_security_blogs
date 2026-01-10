package com.example.blogs.services;

import com.example.blogs.domain.CreatePostRequest;
import com.example.blogs.domain.entities.Post;
import com.example.blogs.domain.entities.User;

import java.util.List;
import java.util.UUID;

public interface PostService {

    List<Post> getAllPosts(UUID categoryId, UUID tagId);

    List<Post> getDraftPosts(User user);

    Post createPost(User user, CreatePostRequest createPostRequest);

}
