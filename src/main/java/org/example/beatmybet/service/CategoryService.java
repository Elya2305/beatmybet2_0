package org.example.beatmybet.service;

import org.example.beatmybet.dto.CategoryAddSubDto;
import org.example.beatmybet.dto.CategoryDto;
import org.example.beatmybet.entity.Category;

import java.util.List;

public interface CategoryService {

    CategoryDto create(CategoryDto categoryDto);

    CategoryDto update(CategoryDto categoryDto);

    List<CategoryDto> getMainCategories();

    List<CategoryDto> getSubcategories(Long id);

    CategoryDto getById(Long id);

    CategoryDto getDtoByTitle(String title);

    Category getEntityByTitle(String title);

    CategoryDto addSubCategory(CategoryAddSubDto dto);
}
