package com.example.blogs.services.impl;

import com.example.blogs.domain.entities.Tag;
import com.example.blogs.repos.TagRepo;
import com.example.blogs.services.TagService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
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

    @Transactional
    @Override
    public void deleteTag(UUID id) {
        tagRepo.findById(id).ifPresent(tag-> {
            if(!tag.getPosts().isEmpty()) {
                    throw new IllegalStateException("cannot delete tag with posts");
            }
            tagRepo.deleteById(id);
        });
    }

    @Override
    public Tag getTagById(UUID id) {
        return tagRepo.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("tag not found"));
    }

    @Override
    public List<Tag> getTagByIds(Set<UUID> ids) {
        List<Tag> foundTags = tagRepo.findAllById(ids);
        if(foundTags.size() != ids.size()) {
            throw new EntityNotFoundException("Not all tags found");
        }
        return foundTags;
    }

}
