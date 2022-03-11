package uz.pdp.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.warehouse.entity.Category;
import uz.pdp.warehouse.payload.CategoryDto;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;


    public Result addCategory(CategoryDto categoryDto){
        if (categoryRepository.existsByName(categoryDto.getName()))
            return new Result("Category name already exist!",false);
        Category category = new Category();
        category.setName(categoryDto.getName());
        if (categoryDto.getParentCategoryId() != null){
            Optional<Category> optionalCategory = categoryRepository.findById(categoryDto.getParentCategoryId());
            if (!optionalCategory.isPresent())
                return new Result("There is not this parent category!",false);
            Optional<Category> categoryOptional = categoryRepository.findById(categoryDto.getParentCategoryId());
            if (!categoryOptional.isPresent())
                return new Result("Category not found!",false);
            category.setParentCategoryId(categoryOptional.get());
        }
        categoryRepository.save(category);
        return new Result("Successfully added!",true);
    }

    public Result editCategory(Integer id,CategoryDto categoryDto){
        Optional<Category> optional = categoryRepository.findById(id);
        if (!optional.isPresent())
            return new Result("Category not found!",false);
        Category category = optional.get();
        if (categoryRepository.existsByNameNot(categoryDto.getName()))
            return new Result("Already exist name!",false);
        if (categoryDto.getParentCategoryId() != null){
            Optional<Category> optionalParent = categoryRepository.findById(categoryDto.getParentCategoryId());
            category.setParentCategoryId(optionalParent.get());
        }
        category.setName(categoryDto.getName());
        return new Result("Successfully edited!",true);
    }

    public List<Category> all(){
        return categoryRepository.findAll();
    }

    public Category getById(Integer id){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        return optionalCategory.orElseGet(Category::new);
    }

    public Result deletedCategory(Integer id){
        Optional<Category> byId = categoryRepository.findById(id);
        if (byId.isPresent()){
            categoryRepository.deleteById(id);
            return new Result("Successfully deleted!",true);
        }
        return new Result("Deleted error!",false);
    }
}
