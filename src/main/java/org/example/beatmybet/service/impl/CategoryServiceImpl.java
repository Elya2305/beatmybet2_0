package org.example.beatmybet.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.beatmybet.dto.CategoryAddSubDto;
import org.example.beatmybet.dto.CategoryDto;
import org.example.beatmybet.entity.Category;
import org.example.beatmybet.exception.NotFoundException;
import org.example.beatmybet.exception.ValidationException;
import org.example.beatmybet.repository.CategoryRepository;
import org.example.beatmybet.service.CategoryService;
import org.example.beatmybet.utils.Validator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public CategoryDto create(CategoryDto categoryDto) {
        if (Validator.validateCategory(categoryDto) && Objects.isNull(categoryDto.getId())) {
            Category category = mapToEntity.apply(categoryDto);
            Category save = categoryRepository.save(category);
            return mapToCategoryDto.apply(save);
        }
        throw new ValidationException("Can't create category " + categoryDto);
    }

    @Override
    public CategoryDto update(CategoryDto categoryDto) {
        if (Validator.validateCategory(categoryDto) && Objects.nonNull(categoryDto.getId())) {
            Category category = categoryRepository.findById(categoryDto.getId()).orElseThrow(NotFoundException::new);
            category.setTitle(categoryDto.getTitle());
            Category save = categoryRepository.save(category);
            return mapToCategoryDto.apply(save);
        }
        throw new ValidationException("Can't create category " + categoryDto);
    }

    @Override
    public CategoryDto addSubCategory(CategoryAddSubDto dto) {
        if (Objects.nonNull(dto) && Objects.nonNull(dto.getIdParent()) && Objects.nonNull(dto.getTitle())) {
            Category parentCategory = categoryRepository.findById(dto.getIdParent()).orElseThrow(NotFoundException::new);
            Category category = new Category();
            category.setTitle(dto.getTitle());
            parentCategory.addSubCategory(category);
            return mapToCategoryDto.apply(categoryRepository.save(parentCategory));
        }
        throw new ValidationException("Can't add subcategory " + dto);
    }

    @Override
    public List<CategoryDto> getMainCategories() {
        return categoryRepository.findAll()
                .stream()
                .filter(o -> Objects.isNull(o.getCategory()))
                .map(mapToCategoryDto)
                .collect(toList());
    }

    @Override
    public List<CategoryDto> getSubcategories(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No category with id " + id))
                .getSubCategories()
                .stream()
                .map(mapToCategoryDto)
                .collect(toList());
    }

    @Override
    public CategoryDto getById(Long id) {
        return categoryRepository.findById(id)
                .map(mapToCategoryDto)
                .orElseThrow(() -> new NotFoundException("No category with id " + id));
    }

    @Override
    public CategoryDto getDtoByTitle(String title) {
        return mapToCategoryDto.apply(categoryRepository.findByTitle(title));
    }

    @Override
    public Category getEntityByTitle(String title) {
        Category category = categoryRepository.findByTitle(title);
        if (category != null) {
            return category;
        }
        throw new NotFoundException("There's no category with title " + title);
    }

    private final Function<Category, CategoryDto> mapToCategoryDto = (category -> CategoryDto.builder()
            .id(category.getId())
            .title(category.getTitle())
            .subCategories(category.getSubCategories().stream()
                    .map(subCat -> CategoryDto.builder()
                            .id(subCat.getId())
                            .title(subCat.getTitle())
                            .build())
                    .collect(Collectors.toList()))
            .build());

    private final Function<CategoryDto, Category> mapToEntity = (dto -> {
        Category category = new Category();
        category.setTitle(dto.getTitle());
        if(Objects.nonNull(dto.getSubCategories())) {
            category.setSubCategories(dto.getSubCategories().stream().map(o -> {
                Category subcategory = new Category();
                subcategory.setTitle(o.getTitle());
                subcategory.setCategory(category);
                return subcategory;
            }).collect(toList()));
        }
        return category;
    });
}
