package com.example.blogs.services;

import com.example.blogs.domain.entities.Tag;

import java.util.List;
import java.util.Set;

public interface TagService {

    List<Tag> getTags();

    List<Tag> createTags(Set<String> tagNames);

}
