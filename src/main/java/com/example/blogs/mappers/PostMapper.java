package com.example.blogs.mappers;

import com.example.blogs.domain.CreatePostRequest;
import com.example.blogs.domain.dtos.CreatePostRequestDto;
import com.example.blogs.domain.dtos.PostDto;
import com.example.blogs.domain.entities.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostMapper {

    @Mapping(target = "author", source = "author")
    @Mapping(target = "category", source = "category")
    @Mapping(target = "tags", source = "tags")
    PostDto toDto(Post post);

    @Mapping(target = "readingTime", source = "readingTime")
    CreatePostRequest toCreatePostRequest(CreatePostRequestDto dto);

}
