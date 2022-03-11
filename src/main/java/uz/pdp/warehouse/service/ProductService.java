package uz.pdp.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.warehouse.entity.Attachment;
import uz.pdp.warehouse.entity.Category;
import uz.pdp.warehouse.entity.Measurement;
import uz.pdp.warehouse.entity.Product;
import uz.pdp.warehouse.payload.ProductDto;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.repository.AttachmentRepository;
import uz.pdp.warehouse.repository.CategoryRepository;
import uz.pdp.warehouse.repository.MeasurementRepository;
import uz.pdp.warehouse.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    MeasurementRepository measurementRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    UserService userService;

    public Result addProduct(ProductDto productDto){
        boolean exists = productRepository.existsByNameAndCategoryId(productDto.getName(), productDto.getCategoryId());
        if (exists)
            return new Result("This name such an category already exist!",false);
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (!optionalCategory.isPresent())
            return new Result("Category not found!",false);
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDto.getMeasurementId());
        if (!optionalMeasurement.isPresent())
            return new Result("Measurement not found!",false);
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getPhotoId());
        if (!optionalAttachment.isPresent())
            return new Result("Photo not found!",false);
        Product product=new Product();
        product.setName(productDto.getName());
        product.setCode(userService.code());
        product.setMeasurement(optionalMeasurement.get());
        product.setPhoto(optionalAttachment.get());
        product.setCategory(optionalCategory.get());
        productRepository.save(product);
        return new Result("Successfully added!",true);
    }

    public List<Product> allProduct(){
        return productRepository.findAll();
    }

    public Product getById(@PathVariable Integer id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        return optionalProduct.orElseGet(Product::new);
    }

    public Result editProduct(@PathVariable Integer id, @RequestBody ProductDto productDto){
        boolean exists = productRepository.existsByNameAndCategoryId(productDto.getName(), productDto.getCategoryId());
        if (exists)
            return new Result("This name such an category already exist!",false);
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (!optionalCategory.isPresent())
            return new Result("Category not found!",false);
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDto.getMeasurementId());
        if (!optionalMeasurement.isPresent())
            return new Result("Measurement not found!",false);
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getPhotoId());
        if (!optionalAttachment.isPresent())
            return new Result("Photo not found!",false);
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (!optionalProduct.isPresent())
            return new Result("There is no product such an id!",false);
        Product product = optionalProduct.get();
        product.setName(productDto.getName());
        product.setCategory(optionalCategory.get());
        product.setMeasurement(optionalMeasurement.get());
        product.setCode(userService.code());
        product.setPhoto(optionalAttachment.get());
        productRepository.save(product);
        return new Result("Successfully edited!",true);
    }

    public Result deleteProduct(@PathVariable Integer id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (!optionalProduct.isPresent())
            return new Result("There is no product such an id!",false);
        productRepository.deleteById(id);
        return new Result("Successfully deleted!",true);
    }
}












