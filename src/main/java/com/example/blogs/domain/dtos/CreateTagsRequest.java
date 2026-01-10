package com.example.blogs.domain.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateTagsRequest {

    @NotEmpty(message = "at least one tag name is required")
    @Size(max = 10, message = "maximum: {max} tags allowed")

    private Set<
            @Size(min = 2, max = 30, message = "between {min}: {max} chars allowed")
                    String> names;

}
