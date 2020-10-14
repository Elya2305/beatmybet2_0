package org.example.beatmybet.controller;

import lombok.RequiredArgsConstructor;
import org.example.beatmybet.dto.CategoryAddSubDto;
import org.example.beatmybet.dto.CategoryDto;
import org.example.beatmybet.service.CategoryService;
import org.example.beatmybet.web.ApiResponse;
import org.example.beatmybet.web.Responses;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/main")
    public ApiResponse<List<CategoryDto>> getMainCategories() {
        return Responses.okResponse(categoryService.getMainCategories());
    }

    @GetMapping("/subcategories")
    public ApiResponse<List<CategoryDto>> getSubcategories(@RequestParam Long id) {
        return Responses.okResponse(categoryService.getSubcategories(id));

    }

    @GetMapping
    public ApiResponse<CategoryDto> getById(@RequestParam Long id) {
        return Responses.okResponse(categoryService.getById(id));
    }

    @PostMapping("/new")
    public ApiResponse<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {
        return Responses.okResponse(categoryService.create(categoryDto));
    }


    @PostMapping("/add")
    public ApiResponse<CategoryDto> addSubCategory(@RequestBody CategoryAddSubDto categoryDto) {
        return Responses.okResponse(categoryService.addSubCategory(categoryDto));
    }

    @PutMapping("/update")
    public ApiResponse<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto) {
        return Responses.okResponse(categoryService.update(categoryDto));
    }
}
