package uz.pdp.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouse.entity.Category;
import uz.pdp.warehouse.payload.CategoryDto;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping
    public Result addCategory(CategoryDto categoryDto){
        return categoryService.addCategory(categoryDto);
    }

    @GetMapping
    public List<Category> all(){
        return categoryService.all();
    }

    @GetMapping("/{id}")
    public Category getById(@PathVariable Integer id){
        return categoryService.getById(id);
    }

    @PutMapping("/{id}")
    public Result editCategory(@PathVariable Integer id,CategoryDto categoryDto){
        return categoryService.editCategory(id,categoryDto);
    }

    @DeleteMapping("/{id}")
    public Result deleteCategory(@PathVariable Integer id){
        return categoryService.deletedCategory(id);
    }
}
