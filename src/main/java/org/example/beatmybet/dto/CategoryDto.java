package org.example.beatmybet.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class CategoryDto {
    private Long id;

    private String title;

    private List<CategoryDto> subCategories;
}
