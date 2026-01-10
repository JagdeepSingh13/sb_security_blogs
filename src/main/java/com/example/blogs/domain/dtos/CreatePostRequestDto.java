package com.example.blogs.domain.dtos;

import com.example.blogs.domain.PostStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreatePostRequestDto {

    @NotBlank(message = "title is required")
    @Size(min = 3, max = 200)
    private String title;

    @NotBlank(message = "content is required")
    private String content;

    @NotNull(message = "category Id is required")
    private UUID categoryId;

    @Builder.Default
    @Size(max = 10)
    private Set<UUID> tagIds = new HashSet<>();

    @NotNull(message = "post status is required")
    private PostStatus status;

    @NotNull(message = "reading time is required")
    private Integer readingTime;

}
