package com.example.blogs.services.impl;

import com.example.blogs.domain.entities.Tag;
import com.example.blogs.repos.TagRepo;
import com.example.blogs.services.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepo tagRepo;

    @Override
    public List<Tag> getTags() {
        return tagRepo.findAllWithPostCount();
    }

    @Transactional
    @Override
    public List<Tag> createTags(Set<String> tagNames) {
//        find all existing tags and change them to String and store in Set
        List<Tag> existingTags = tagRepo.findByNameIn(tagNames);

        Set<String> existingTagNames =  existingTags.stream()
                .map(Tag::getName)
                .collect(Collectors.toSet());

//        if new then make a new Tag
        List<Tag> newTags = tagNames.stream()
                .filter(name-> !existingTagNames.contains(name))
                .map(name -> Tag.builder()
                        .name(name)
                        .posts(new HashSet<>())
                        .build())
                .toList();

        List<Tag> savedTags = new ArrayList<>();
        if(!newTags.isEmpty()) {
            savedTags = tagRepo.saveAll(newTags);
        }

        savedTags.addAll(existingTags);

        return savedTags;
    }

}
