package com.example.blogs.controllers;

import com.example.blogs.domain.dtos.CreateTagsRequest;
import com.example.blogs.domain.dtos.TagResponse;
import com.example.blogs.domain.entities.Tag;
import com.example.blogs.mappers.TagMapper;
import com.example.blogs.services.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/tags")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;
    private final TagMapper tagMapper;

    @GetMapping
    public ResponseEntity<List<TagResponse>> getAllTags() {
        List<Tag> tags = tagService.getTags();
        List<TagResponse> tagResponses = tags.stream()
                .map(tagMapper::toTagResponse).toList();

        return ResponseEntity.ok(tagResponses);
    }

    @PostMapping
    public ResponseEntity<List<TagResponse>> createTags(@RequestBody CreateTagsRequest createTagsRequest) {
        List<Tag> savedTags = tagService.createTags(createTagsRequest.getNames());
        List<TagResponse> createdTagResponses = savedTags.stream()
                .map(tagMapper::toTagResponse).toList();

        return new ResponseEntity<>(createdTagResponses, HttpStatus.CREATED);
    }

}
