package com.example.blogs.services.impl;

import com.example.blogs.domain.entities.Tag;
import com.example.blogs.repos.TagRepo;
import com.example.blogs.services.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepo tagRepo;

    @Override
    public List<Tag> getTags() {
        return tagRepo.findAllWithPostCount();
    }

}
